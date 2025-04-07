package com.crypto.service;

import com.crypto.dto.WalletDto;
import com.crypto.model.Wallet;
import com.crypto.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@EnableScheduling

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    public Wallet createWallet(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    public List<WalletDto> findAll() {
        return walletRepository.findAll()
                .stream().map(WalletDto::fromEntity).collect(Collectors.toList());
    }

    public ResponseEntity<Wallet> findById(String id) {
        return walletRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public void delete(String id) {
       this.walletRepository.findById(id).ifPresent(walletRepository::delete);
    }

    public Wallet update(String id, Wallet wallet) {
        Wallet body = this.findById(id).getBody();
        if(body != null) {
            this.walletRepository.save(body);
        }
        return body;
    }
}