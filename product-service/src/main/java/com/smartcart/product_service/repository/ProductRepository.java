package com.smartcart.product_service.repository;

import com.smartcart.product_service.dto.Product;
import com.smartcart.product_service.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {

    boolean existsByNameIgnoreCaseAndCategoryIgnoreCase(String name, String category);

    List<ProductEntity> findByNameContainingIgnoreCaseAndCategoryContainingIgnoreCase(String keyword, String keyword1);
}
