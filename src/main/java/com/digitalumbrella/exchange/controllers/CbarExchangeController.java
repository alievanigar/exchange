package com.digitalumbrella.exchange.controllers;

import com.digitalumbrella.exchange.interfaces.CbarExchangeInter;
import com.digitalumbrella.exchange.models.request.AuthenticationRequest;
import com.digitalumbrella.exchange.models.response.ApiResponse;
import com.digitalumbrella.exchange.models.response.AuthenticationResponse;
import com.digitalumbrella.exchange.services.MyUserDetailsService;
import com.digitalumbrella.exchange.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class CbarExchangeController {

    private final CbarExchangeInter cbarExchangeInter;

    private final AuthenticationManager authenticationManager;

    private final MyUserDetailsService userDetailsService;

    private final JwtUtil jwtTokenUtil;

    @PostMapping("/saveInfoExchange/{date}")
    public ApiResponse saveInfoExhange(@PathVariable @DateTimeFormat(pattern = "dd.MM.yyyy")  Date date){
        return  cbarExchangeInter.saveInfoExchange(date);
    }

    @DeleteMapping("/deleteInfoExchange/{date}")
    public ApiResponse deleteInfoExhange(@PathVariable @DateTimeFormat(pattern = "dd.MM.yyyy")  Date date) {
        return cbarExchangeInter.deleteInfoExchange(date);
    }

        @PostMapping( "/authenticate")
        public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

            try {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
                );
            }
            catch (BadCredentialsException e) {
                throw new Exception("Incorrect username or password", e);
            }


            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(authenticationRequest.getUsername());

            final String jwt = jwtTokenUtil.generateToken(userDetails);

            return ResponseEntity.ok(new AuthenticationResponse(jwt));
        }
    }

