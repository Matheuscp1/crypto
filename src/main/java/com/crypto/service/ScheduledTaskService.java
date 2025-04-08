package com.crypto.service;

import com.crypto.model.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Service
public class ScheduledTaskService {

    @Value("${api-x-key}")
    private String secret;

    @Value("${url.api}")
    private String url;

    @Autowired
    private AssetService  assetService;

    @Autowired
    CryptoPriceService cryptoPriceService;

    @Scheduled(fixedRate = 1200000)
    public void updatePrices() {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<String> tokens = Arrays .asList("BTC", "ETH", "XRP","USDT","BNB","USDC","SOL","DOGE","TRX","ADA");
        for (String token : tokens) {
            List<Asset> symbol = assetService.findBySymbol(token);
            if(symbol != null && symbol.size() > 0) {
                executor.submit(() -> {
                    BigDecimal price = cryptoPriceService.getTokenPrice(token);
                    int i = assetService.updateAssetByPrice(token, price);
                });
            }
        }
    }
}


