package com.digitalumbrella.exchange.services;

import com.digitalumbrella.exchange.entities.InfoExchange;
import com.digitalumbrella.exchange.enums.ResponseCodes;
import com.digitalumbrella.exchange.interfaces.CbarExchangeInter;
import com.digitalumbrella.exchange.models.response.ApiResponse;
import com.digitalumbrella.exchange.reporitories.InfoExchangeRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CbarExchangeService  implements CbarExchangeInter {

    private final InfoExchangeRepository infoExchangeRepository;
    private static final Logger LOG = LoggerFactory.getLogger(CbarExchangeService.class);

    public ApiResponse saveInfoExchange(Date date) {
        ApiResponse response = new ApiResponse();
        try {
            List<InfoExchange> findByDate = infoExchangeRepository.findByDat(date);
            if (findByDate.isEmpty() || findByDate == null) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                String url = "https://www.cbar.az/currencies/" + sdf.format(date) + ".xml";
                Document doc = Jsoup.connect(url).get();
                Elements elements = doc.select("Valute");
                for (Element element : elements) {
                    InfoExchange infoExchange = new InfoExchange();
                    infoExchange.setCode(element.attr("Code"));
                    infoExchange.setName(element.getElementsByTag("Name").text());
                    infoExchange.setValue(element.getElementsByTag("Value").text());
                    infoExchange.setDat(date);
                    infoExchangeRepository.save(infoExchange);
                }
                response.setResponse("Məlumatlar uğurla əlavə olundu.");
            } else {
                response.setResponse("Qeyd edilən tarixi üçün məlumatlar artıq mövcuddur!");
            }
            response.setCode(ResponseCodes.SUCCESS);

        } catch (Exception e) {
            LOG.error("Exception saveInfoExchange {}", e);
            response.setCode(ResponseCodes.UNKNOWN_ERROR);
        }

        return response;

    }

    public ApiResponse deleteInfoExchange(Date date) {
        ApiResponse response = new ApiResponse();
        try {
            List<InfoExchange> findByDate = infoExchangeRepository.findByDat(date);
            if(!(findByDate.isEmpty() || findByDate == null)){
               for (InfoExchange f: findByDate) {
                   infoExchangeRepository.delete(f);
               }
               response.setResponse("Məlumatlar uğurla silindi");
            }
            else{
                response.setResponse("Qeyd edilən tarix üçün məlumat tapılmadı");
            }
            response.setCode(ResponseCodes.SUCCESS);

        } catch (Exception e) {
            LOG.error("Exception deleteInfoExchange {}",e);
            response.setCode(ResponseCodes.UNKNOWN_ERROR);
        }
        return response;
    }

}