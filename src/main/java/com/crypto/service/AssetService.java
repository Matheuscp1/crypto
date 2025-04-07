package com.crypto.service;

import com.crypto.model.Asset;
import com.crypto.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.util.List;

@EnableScheduling

@Service
public class AssetService {

    @Autowired
    private AssetRepository assetRepository;

    public Asset createAsset(Asset asset) {
        return assetRepository.save(asset);
    }

    public List<Asset> findAll() {
        return assetRepository.findAll();
    }

    public ResponseEntity<Asset> findById(String id) {
        return assetRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public void delete(String id) {
       this.assetRepository.findById(id).ifPresent(assetRepository::delete);
    }

    public Asset update(String id, Asset asset) {
        Asset body = this.findById(id).getBody();
        if(body != null) {

            this.assetRepository.save(body);
        }
        return body;
    }
}