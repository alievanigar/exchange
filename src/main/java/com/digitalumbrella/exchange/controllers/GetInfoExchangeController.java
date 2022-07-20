package com.digitalumbrella.exchange.controllers;

import com.digitalumbrella.exchange.interfaces.GetInfoExchangeInter;
import com.digitalumbrella.exchange.models.response.ApiResponse;
import com.digitalumbrella.exchange.services.GetInfoExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class GetInfoExchangeController {

    private final GetInfoExchangeInter getInfoExchangeInter;

    @GetMapping("/getInfoExchange")
    public ApiResponse saveInfoExhange(@RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") Optional<Date> date, @RequestParam Optional<String> valyuta){
        return  getInfoExchangeInter.getInfoExchange(date.orElse(null),valyuta.orElse(null));
    }
}
