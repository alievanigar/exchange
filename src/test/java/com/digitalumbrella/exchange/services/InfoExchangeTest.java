package com.digitalumbrella.exchange.services;

import com.digitalumbrella.exchange.entities.InfoExchange;
import com.digitalumbrella.exchange.reporitories.InfoExchangeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest
 class InfoExchangeTest {

    @Autowired
    GetInfoExchangeService getInfoExchangeService;
    @MockBean
    private InfoExchangeRepository infoExchangeRepository;



    @Test
    void getInfoExchange()
    {
       when(infoExchangeRepository.findWithParam(null,null)).thenReturn(Stream.
               of(new InfoExchange(1L,"test1","test2","test3",new Date()),
                       new InfoExchange(2L,"test1_1","test2_1","test3_1",
                               new Date())).collect(Collectors.toList()).stream().collect(Collectors.toList()));
   assertEquals(2,((List<InfoExchange>)getInfoExchangeService.getInfoExchange(null,null).getResponse()).size());
    }


}
