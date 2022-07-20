package com.digitalumbrella.exchange.models.response;

import com.digitalumbrella.exchange.enums.ResponseCodes;
import lombok.Data;

@Data
public class ApiResponse {

    private ResponseCodes code;
    private Object response;
}
