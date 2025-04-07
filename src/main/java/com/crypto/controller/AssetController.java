package com.crypto.controller;

import com.crypto.model.Asset;
import com.crypto.service.AssetService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

@RestController
@RequestMapping("/api/asset")
@AllArgsConstructor
public class AssetController {

    @Autowired
    private AssetService assetService;

    @GetMapping("")
    public List<Asset> getUsers() {
        return assetService.findAll();
    }

    @GetMapping("/{id}")
    public Asset getUsers(@PathVariable String id) {
        return assetService.findById(id).getBody();
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping()
    public Asset save(@RequestBody Asset asset) {
        return assetService.createAsset(asset);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        assetService.delete(id);
    }

    @PutMapping("/{id}")
    public Asset update(@PathVariable String id,@RequestBody Asset asset) {
        return assetService.update(id,asset);
    }


}
