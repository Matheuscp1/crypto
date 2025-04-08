package com.crypto.controller;

import com.crypto.dto.AssetDto;
import com.crypto.dto.AssetInputDto;
import com.crypto.model.Asset;
import com.crypto.openapi.AssetControllerOpenApi;
import com.crypto.service.AssetService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/asset")
@AllArgsConstructor
public class AssetController implements AssetControllerOpenApi {

    @Autowired
    private AssetService assetService;

    @GetMapping("")
    public List<AssetDto> getAssets() {
        return assetService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<AssetDto> getAssetById(@PathVariable String id) {
        return assetService.findById(id);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping()
    public AssetDto save(@RequestBody @Validated AssetInputDto asset) {
        return assetService.createAsset(asset);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        assetService.delete(id);
    }

    @PutMapping("/{id}")
    public AssetDto update(@PathVariable String id,@RequestBody @Validated  AssetInputDto asset) {
        return assetService.update(id,asset);
    }


}
