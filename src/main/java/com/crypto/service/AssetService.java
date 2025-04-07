package com.crypto.service;

import com.crypto.dto.AssetDto;
import com.crypto.dto.AssetInputDto;
import com.crypto.model.Asset;
import com.crypto.model.Wallet;
import com.crypto.repository.AssetRepository;
import com.crypto.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@EnableScheduling

@Service
public class AssetService {

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private WebClient webClient;


    public AssetDto createAsset(AssetInputDto asset) {
        Asset assetEntity = asset.toEntitie();

        Wallet wallet = walletRepository.findById(asset.getWalletId()).orElse(null);
        if(wallet != null) {
            assetEntity.setWallet(wallet);
            assetRepository.save(assetEntity);
            return AssetDto.fromEntity(assetEntity);
        }
        throw new IllegalArgumentException("Wallet not found");
    }

    public List<AssetDto> findAll() {
        String COINCAP_API_URL = "https://api.coincap.io/v2/assets/";
            String url = COINCAP_API_URL + "BTC";
            try{
                Map block = this.webClient
                        .get()
                        .uri(url)
                        .retrieve()
                        .bodyToMono(Map.class)
                        .block();
                System.out.println(block);
            }catch (Exception e){
                e.printStackTrace();
            }
        return assetRepository.findAll()
                .stream().map(AssetDto::fromEntity).collect(Collectors.toList());

    }

    public Optional<AssetDto> findById(String id) {
        return assetRepository.findById(id)
                .map(AssetDto::fromEntity);
    }

    public void delete(String id) {
       this.assetRepository.findById(id).ifPresent(assetRepository::delete);
    }

    public AssetDto update(String id, AssetInputDto asset) {
        Optional<Asset> body = assetRepository.findById(id);
        if(body.isPresent()) {
            Optional<Wallet> wallet = walletRepository.findById(asset.getWalletId());
            if(wallet.isEmpty()) {
                throw new IllegalArgumentException("Wallet not found");
            }
            Asset assetEntitie = asset.toEntitie();
            assetEntitie.setId(id);
            assetEntitie.setWallet(wallet.get());
            this.assetRepository.save(assetEntitie);
            return AssetDto.fromEntity(assetEntitie);

        }
        throw new IllegalArgumentException("Asset not found");
    }
}