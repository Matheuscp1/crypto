package com.crypto.dto;

import com.crypto.model.Asset;
import com.crypto.model.Wallet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
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
    private BigDecimal total = BigDecimal.ZERO;
    private List<AssetDto> assets;

    public static WalletDto fromEntity(Wallet wallet) {
        BigDecimal  total = BigDecimal.ZERO;
        List<AssetDto> assets = new ArrayList<>();
        for (Asset asset : wallet.getAsset()) {
            total = total.add(asset.getValue());
            AssetDto assetDto = new AssetDto(asset.getId(), asset.getSymbol(),
                    asset.getQuantity(),
                    asset.getPrice(), asset.getValue(), null);
            assets.add(assetDto);
        }
        return WalletDto.builder()
                .id(wallet.getId())
                .email(wallet.getEmail())
                .assets(assets)
                .total(total)
                .build();
    }
}