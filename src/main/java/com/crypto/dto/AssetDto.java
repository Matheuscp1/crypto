package com.crypto.dto;

import com.crypto.model.Asset;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssetDto {
    private String id;
    private String symbol;
    private BigDecimal quantity;
    private BigDecimal price;
    private BigDecimal value;
    private WalletGroupDto wallet;

    public AssetDto(String id, String symbol, BigDecimal quantity,
                    BigDecimal price, BigDecimal value) {
    }

    public static AssetDto fromEntity(Asset asset) {
        return AssetDto.builder()
                .id(asset.getId())
                .symbol(asset.getSymbol())
                .quantity(asset.getQuantity())
                .price(asset.getPrice())
                .value(asset.getValue())
                .wallet(WalletGroupDto.fromEntity(asset.getWallet()))
                .build();
    }
}