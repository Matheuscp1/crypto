package com.crypto.openapi;

import com.crypto.dto.AssetDto;
import com.crypto.dto.AssetInputDto;
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

@Tag(name = "Assets")
public interface AssetControllerOpenApi {

    @Operation(summary = "List assets")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content())
    })
    List<AssetDto> getAssets();

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content())
    })
    @Operation(summary = "List asset by id")
    Optional<AssetDto> getAssetById(@Parameter(description = "Id ", example = "aId", required = true) final String id);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content())
    })
    @Operation(summary = "Create assets")
    AssetDto save(@RequestBody AssetInputDto category);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content())
    })
    @Operation(summary = "Update asset")
    void delete(@Parameter(description = "Id ", example = "aId", required = true) final String id);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content())
    })
    @Operation(summary = "Delete by id")
    AssetDto update(@Parameter(description = "Id ", example = "aId", required = true) final String id,
                  @RequestBody AssetInputDto category);


}

