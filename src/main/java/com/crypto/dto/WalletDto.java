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
public class WalletDto {
    private String id;
    private String email;
    private List<AssetDto> assets;

    public static WalletDto fromEntity(Wallet wallet) {
        return WalletDto.builder()
                .id(wallet.getId())
                .email(wallet.getEmail())
                .assets(wallet.getAsset() != null ? wallet.getAsset().stream()
                        .map(asset -> new AssetDto(asset.getId(), asset.getSymbol(),
                                asset.getQuantity(),
                                asset.getPrice(), asset.getValue()))
                        .collect(Collectors.toList()) : new ArrayList<>())
                .build();
    }
}