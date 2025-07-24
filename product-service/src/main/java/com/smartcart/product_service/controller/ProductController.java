package com.smartcart.product_service.controller;

import com.smartcart.product_service.dto.Product;
import com.smartcart.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product add(@RequestBody Product product){
       return productService.addProduct(product);
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product update(@PathVariable Long id, Product product){
       return productService.updateProduct(id, product);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void delete(@PathVariable Long id){
        productService.deleteProduct(id);
    }
    @DeleteMapping("/{id}/remove")
    @ResponseStatus(HttpStatus.OK)
    public void remove(@PathVariable Long id){
        productService.removeProduct(id);
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Product getById(@PathVariable Long id){
        return productService.getProductById(id);
    }

}
