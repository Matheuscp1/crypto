package com.crypto.dto;

import com.crypto.model.Wallet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalletGroupDto {
    private String id;
    private String email;

    public static WalletGroupDto fromEntity(Wallet wallet) {
        return WalletGroupDto.builder()
                .id(wallet.getId())
                .email(wallet.getEmail())
                .build();
    }
}