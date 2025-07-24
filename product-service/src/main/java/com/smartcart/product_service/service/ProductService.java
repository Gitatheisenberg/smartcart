package com.smartcart.product_service.service;

import com.smartcart.product_service.dto.Product;

import java.util.List;

public interface ProductService {

    Product addProduct(Product product);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);

    void removeProduct(Long id);

    List<Product> searchProducts(String keyword);
    Product getProductById(Long id);

}
