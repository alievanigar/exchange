package com.digitalumbrella.exchange.interfaces;

import com.digitalumbrella.exchange.models.response.ApiResponse;

import java.util.Date;

public interface GetInfoExchangeInter {

    ApiResponse getInfoExchange(Date dat, String valyuta);
}
