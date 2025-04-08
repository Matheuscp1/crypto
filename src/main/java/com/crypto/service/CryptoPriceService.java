package com.crypto.service;

import com.crypto.dto.CryptoPriceDataResponseDto;
import com.crypto.dto.CryptoPriceResponseDto;
import com.crypto.model.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@EnableScheduling
@Service
public class CryptoPriceService {

    @Value("${api-x-key}")
    private String secret;

    @Value("${url.api}")
    private String url;

    @Autowired
    AssetService assetService;

    @Autowired
    private  WebClient webClient;

    public BigDecimal getTokenPrice(String symbol) {
        String url = this.url +"?search="+symbol+"&apiKey="+secret;
        try{
            CryptoPriceDataResponseDto block = this.webClient
                    .get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(CryptoPriceDataResponseDto.class)
                    .block();
            return block.getData().get(0).getPriceUsd();

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
    @Scheduled(fixedRate = 1200000)
    public void updatePrices() {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<String> tokens = Arrays .asList("BTC", "ETH", "XRP","USDT","BNB","USDC","SOL","DOGE","TRX","ADA");
        for (String token : tokens) {
            List<Asset> symbol = assetService.findBySymbol(token);
            if(symbol != null && symbol.size() > 0) {
                executor.submit(() -> {
                    BigDecimal price = this.getTokenPrice(token);
                    int i = assetService.updateAssetByPrice(token, price);
                    System.out.println("salvou o token: " + token + " linhas: " + i);
                });
            }
        }
    }
}


