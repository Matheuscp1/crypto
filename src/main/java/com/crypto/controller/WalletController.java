package com.crypto.controller;

import com.crypto.dto.WalletDto;
import com.crypto.dto.WalletInputDto;
import com.crypto.model.Wallet;
import com.crypto.service.WalletService;
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
@RequestMapping("/api/wallet")
@AllArgsConstructor
public class WalletController {

    @Autowired
    private WalletService walletService;

    @GetMapping("")
    public List<WalletDto> getWallet() {
        return walletService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<WalletDto> getWalletById(@PathVariable String id) {
        return walletService.findById(id);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping()
    public Wallet save(@RequestBody @Validated WalletInputDto wallet) {
        return walletService.createWallet(wallet);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        walletService.delete(id);
    }

    @PutMapping("/{id}")
    public Wallet update(@PathVariable String id,@RequestBody @Validated WalletInputDto wallet) {
        return walletService.update(id,wallet);
    }


}
