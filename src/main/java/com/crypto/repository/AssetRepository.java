package com.crypto.repository;

import com.crypto.model.Asset;
import com.crypto.model.Wallet;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AssetRepository extends JpaRepository<Asset, String> {
    public List<Asset> findBySymbol(String symbol);

    @Transactional
    @Modifying
    @Query("UPDATE Asset p SET p.price = :price WHERE p.symbol = :symbol")
    int updateAssetByPrice(@Param("symbol") String symbol, @Param("price") BigDecimal price);

}
