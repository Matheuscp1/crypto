package com.crypto.service;

import com.crypto.dto.WalletDto;
import com.crypto.dto.WalletInputDto;
import com.crypto.model.Wallet;
import com.crypto.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@EnableScheduling

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    public Wallet createWallet(WalletInputDto wallet) {
        Wallet walletEntity = new Wallet();
        Wallet byEmail = walletRepository.findByEmail(wallet.getEmail());
        if(byEmail == null) {
            walletEntity.setEmail(wallet.getEmail());
            return walletRepository.save(walletEntity);
        }
        throw new IllegalArgumentException("Email already exists");
    }

    public List<WalletDto> findAll() {
        return walletRepository.findAll()
                .stream().map(WalletDto::fromEntity).collect(Collectors.toList());
    }

    public Optional<WalletDto> findById(String id) {
        return walletRepository.findById(id)
                .map(WalletDto::fromEntity);
    }

    public void delete(String id) {
       this.walletRepository.findById(id).ifPresent(walletRepository::delete);
    }

    public Wallet update(String id, WalletInputDto wallet) {
        Optional<Wallet> body = walletRepository.findById(id);
        if(body.isPresent()) {
            if(body.get().getEmail().equals(wallet.getEmail())) {
                throw new IllegalArgumentException("Email already exists");
            }
            body.get().setEmail(wallet.getEmail());
           return this.walletRepository.save(body.get());
        }
        return null;
    }
}