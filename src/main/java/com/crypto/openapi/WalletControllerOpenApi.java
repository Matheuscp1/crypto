package com.crypto.openapi;

import com.crypto.dto.WalletDto;
import com.crypto.dto.WalletInputDto;
import com.crypto.model.Wallet;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Tag(name = "Wallet")
public interface WalletControllerOpenApi {

    @Operation(summary = "List wallet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content())
    })
    List<WalletDto> getWallets();

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content())
    })
    @Operation(summary = "List wallet by id")
    Optional<WalletDto> getWalletById(@Parameter(description = "Id ", example = "aId", required = true) final String id);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content())
    })
    @Operation(summary = "Create wallet")
    Wallet save(@RequestBody WalletInputDto category);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content())
    })
    @Operation(summary = "Upddate uma categoria")
    void delete(@Parameter(description = "Id ", example = "aId", required = true) final String id);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content())
    })
    @Operation(summary = "Lista a categoria por id")
    Wallet update(@Parameter(description = "Id ", example = "aId", required = true) final String id,
                  @RequestBody WalletInputDto category);


}

