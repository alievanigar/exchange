package com.digitalumbrella.exchange.interfaces;

import com.digitalumbrella.exchange.models.response.ApiResponse;

import java.util.Date;

public interface CbarExchangeInter {

    ApiResponse saveInfoExchange(Date date);
    ApiResponse deleteInfoExchange(Date date);


}
