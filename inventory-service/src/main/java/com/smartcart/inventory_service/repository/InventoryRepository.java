package com.smartcart.inventory_service.repository;

import com.smartcart.inventory_service.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {
  Optional<InventoryEntity> findByProductId(Long productId);
    // Custom query methods can be defined here if needed
    // For example, to find inventory by product ID:
    // Optional<InventoryEntity> findByProductId(Long productId);
}
