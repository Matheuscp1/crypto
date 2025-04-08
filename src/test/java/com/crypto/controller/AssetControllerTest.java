package com.crypto.controller;

import com.crypto.CryptoApplication;
import com.crypto.dto.AssetDto;
import com.crypto.dto.AssetInputDto;
import com.crypto.service.AssetService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = CryptoApplication.class)
public class AssetControllerTest {

    @Mock
    private AssetService assetService;

    @InjectMocks
    private AssetController assetController;

    private MockMvc mockMvc;

    private AssetInputDto assetInputDto;
    private AssetDto assetDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(assetController).build();

        assetInputDto = new AssetInputDto();
        assetInputDto.setWalletId("walletId");
        assetInputDto.setSymbol("BTC");
        assetInputDto.setQuantity(new BigDecimal("11"));
        assetInputDto.setPrice(new BigDecimal("12"));
        assetInputDto.setValue(new BigDecimal("155"));
        assetDto = new AssetDto();
        assetDto.setId("assetId");
        assetDto.setSymbol("BTC");
        assetDto.setQuantity(new BigDecimal("56"));
    }

    @Test
    void shouldFindAllAssets() throws Exception {
        when(assetService.findAll()).thenReturn(List.of(assetDto));

        mockMvc.perform(get("/api/asset"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("assetId"))
                .andExpect(jsonPath("$[0].symbol").value("BTC"));

        verify(assetService, times(1)).findAll();
    }

    @Test
    void shouldFindById() throws Exception {
        when(assetService.findById("assetId")).thenReturn(Optional.of(assetDto));

        mockMvc.perform(get("/api/asset/{id}", "assetId"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("assetId"))
                .andExpect(jsonPath("$.symbol").value("BTC"));

        verify(assetService, times(1)).findById("assetId");
    }


    @Test
    void shouldCreateAsset() throws Exception {
        when(assetService.createAsset(any(AssetInputDto.class))).thenReturn(assetDto);

        mockMvc.perform(post("/api/asset")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(assetInputDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("assetId"))
                .andExpect(jsonPath("$.symbol").value("BTC"));

        verify(assetService, times(1)).createAsset(any(AssetInputDto.class));
    }

    @Test
    void shouldDeleteAsset() throws Exception {
        doNothing().when(assetService).delete("assetId");

        mockMvc.perform(delete("/api/asset/{id}", "assetId"))
                .andExpect(status().isNoContent());

        verify(assetService, times(1)).delete("assetId");
    }

    @Test
    void shouldUpdateAsset() throws Exception {
        when(assetService.update("assetId", assetInputDto)).thenReturn(assetDto);

        mockMvc.perform(put("/api/asset/{id}", "assetId")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(assetInputDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("assetId"))
                .andExpect(jsonPath("$.symbol").value("BTC"));

        verify(assetService, times(1)).update("assetId", assetInputDto);
    }
}
