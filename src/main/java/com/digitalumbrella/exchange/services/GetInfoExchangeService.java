package com.digitalumbrella.exchange.services;

import com.digitalumbrella.exchange.entities.InfoExchange;
import com.digitalumbrella.exchange.enums.ResponseCodes;
import com.digitalumbrella.exchange.interfaces.GetInfoExchangeInter;
import com.digitalumbrella.exchange.models.response.ApiResponse;
import com.digitalumbrella.exchange.reporitories.InfoExchangeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetInfoExchangeService implements GetInfoExchangeInter {

    private final InfoExchangeRepository infoExchangeRepository;

public ApiResponse getInfoExchange(Date dat, String valyuta){
    ApiResponse response = new ApiResponse();
    try{
             List<InfoExchange> infoExchangeList=  infoExchangeRepository.findWithParam(dat,valyuta);
       if (!(infoExchangeList.isEmpty()||infoExchangeList==null)) {
           response.setResponse(infoExchangeList);
           response.setCode(ResponseCodes.SUCCESS);
       }else{
           response.setCode(ResponseCodes.NO_DATA_FOUND);
           response.setResponse("Qeyd edilən tarixdə məlumat tapılmadı");
       }

    } catch (Exception e) {
        log.error("Exception getInfoExchange {}",e);
        response.setCode(ResponseCodes.UNKNOWN_ERROR);
    }
        return response;
}


    private Specification<InfoExchange> getSpecInfo(Map<String, Object> queryMap) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            queryMap.keySet()
                    .forEach(key -> {
                            predicates.add(criteriaBuilder.equal(root.get(key), queryMap.get(key)));

                    });

            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        };
    }
}
