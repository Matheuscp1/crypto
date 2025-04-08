package com.crypto.service;

import com.crypto.dto.AssetDto;
import com.crypto.dto.AssetInputDto;
import com.crypto.model.Asset;
import com.crypto.model.Wallet;
import com.crypto.repository.AssetRepository;
import com.crypto.repository.WalletRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class AssetServiceTest {

    @Mock
    private AssetRepository assetRepository;

    @Mock
    private WalletRepository walletRepository;

    @Mock
    private CryptoPriceService cryptoPriceService;

    @InjectMocks
    private AssetService assetService;

    private AssetInputDto assetInputDto;
    private Wallet wallet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        wallet = new Wallet();
        wallet.setId("walletId");

        assetInputDto = new AssetInputDto();
        assetInputDto.setWalletId("walletId");
        assetInputDto.setSymbol("BTC");
        assetInputDto.setQuantity(new BigDecimal("0.5"));
    }

    @Test
    void shouldSaveAsset() {
        when(walletRepository.findById(anyString())).thenReturn(Optional.of(wallet));

        when(cryptoPriceService.getTokenPrice(anyString())).thenReturn(new BigDecimal("50000"));

        AssetDto result = assetService.createAsset(assetInputDto);

        verify(assetRepository, times(1)).save(any(Asset.class));

        assertNotNull(result);
        assertEquals("BTC", result.getSymbol());  // Verifique o valor retornado
    }

    @Test
    void shouldWalletNotFound() {
        when(walletRepository.findById(anyString())).thenReturn(Optional.empty());

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            assetService.createAsset(assetInputDto);
        });

        assertEquals("Wallet not found", thrown.getMessage());
    }

    @Test
    void shouldPriceNotFound() {
        when(walletRepository.findById(anyString())).thenReturn(Optional.of(wallet));

        when(cryptoPriceService.getTokenPrice(anyString())).thenReturn(null);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            assetService.createAsset(assetInputDto);
        });

        assertEquals("Price not found", thrown.getMessage());
    }

    @Test
    void shouldUpdateAsset() {
        Asset existingAsset = new Asset();
        existingAsset.setId("existingAssetId");
        existingAsset.setWallet(wallet);

        when(assetRepository.findById(anyString())).thenReturn(Optional.of(existingAsset));
        when(walletRepository.findById(anyString())).thenReturn(Optional.of(wallet));

        AssetDto updatedAsset = assetService.update("existingAssetId", assetInputDto);

        assertNotNull(updatedAsset);
        assertEquals("existingAssetId", updatedAsset.getId());
    }

    @Test
    void shouldUpdateAssetNotFound() {
        when(assetRepository.findById(anyString())).thenReturn(Optional.empty());

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            assetService.update("nonExistentAssetId", assetInputDto);
        });

        assertEquals("Asset not found", thrown.getMessage());
    }

    @Test
    void shouldDeleteAsset() {
        Asset asset = new Asset();
        asset.setId("assetToDelete");

        when(assetRepository.findById(anyString())).thenReturn(Optional.of(asset));

        assetService.delete("assetToDelete");

        verify(assetRepository, times(1)).delete(any(Asset.class));
    }

}