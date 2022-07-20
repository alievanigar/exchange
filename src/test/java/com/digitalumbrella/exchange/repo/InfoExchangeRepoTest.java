package com.digitalumbrella.exchange.repo;

import com.digitalumbrella.exchange.entities.InfoExchange;
import com.digitalumbrella.exchange.reporitories.InfoExchangeRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class InfoExchangeRepoTest {

    @Autowired
    private InfoExchangeRepository infoExchangeRepository;


    @Test
    @Order(1)
    void testCreate() {
        InfoExchange infoExchange = new InfoExchange();
        infoExchange.setCode("test_code1");
        infoExchange.setName("test_name");
        infoExchange.setDat(new Date());
        infoExchange.setValue("123");
        infoExchangeRepository.save(infoExchange);
        assertNotNull(infoExchangeRepository.findByName("test_name").get(0));
    }

    @Test
    @Order(2)
    void testReadAll() {
       List<InfoExchange> infoExchangeList = infoExchangeRepository.findAll();
        assertThat(infoExchangeList.size()).isGreaterThan(0);
    }

    @Test
    @Order(3)
    void testUpdate() {
        Long i =infoExchangeRepository.findByName("test_name").get(0).getId();
        InfoExchange infoExchange = infoExchangeRepository.findById(i).get();
        infoExchange.setCode("code_test1");
        infoExchangeRepository.save(infoExchange);
        assertNotEquals("test_code",infoExchangeRepository.findById(i).get().getCode());
    }
    @Test
    @Order(4)
    void testDelete() {
        Long i =infoExchangeRepository.findByName("test_name").get(0).getId();
        infoExchangeRepository.deleteById(i);
        assertThat(infoExchangeRepository.existsById(i)).isFalse();
    }
}
