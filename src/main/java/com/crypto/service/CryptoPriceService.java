package com.crypto.service;

import com.crypto.dto.CryptoPriceDataResponseDto;
import com.crypto.model.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Service
public class CryptoPriceService {

    @Value("${api-x-key}")
    private String secret;

    @Value("${url.api}")
    private String url;

    @Autowired
    private WebClient webClient;

    public BigDecimal getTokenPrice(String symbol) {
        String url = this.url + "?search=" + symbol + "&apiKey=" + secret;
        try {
            CryptoPriceDataResponseDto block = this.webClient
                    .get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(CryptoPriceDataResponseDto.class)
                    .block();
            if (block != null) {
                if (block.getData().isEmpty()) throw new IllegalArgumentException("Price not found");
                return block.getData().getFirst().getPriceUsd();
            }

        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        throw new IllegalArgumentException("Price not found");
    }

}


