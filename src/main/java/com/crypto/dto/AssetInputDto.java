package com.crypto.dto;

import com.crypto.model.Asset;
import com.crypto.model.Wallet;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssetInputDto {
    @NotNull
    private String walletId;

    @NotNull
    private String symbol;

    @NotNull
    @Min(value = 1, message = "O price deve ser maior que zero.")
    private BigDecimal quantity;

    @NotNull
    @Min(value = 1, message = "O price deve ser maior que zero.")
    private BigDecimal price;

    @NotNull
    @Min(value = 1, message = "O price deve ser maior que zero.")
    private BigDecimal value;


    public Asset toEntitie() {
        return Asset.builder()
                .symbol(this.symbol)
                .quantity(this.quantity)
                .price(this.price)
                .value(this.value)
                .build();
    }
}