package com.smartcart.product_service.service;
import com.smartcart.product_service.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import com.smartcart.product_service.dto.Product;
import com.smartcart.product_service.entity.ProductEntity;
import com.smartcart.product_service.entity.ProductStatus;
import com.smartcart.product_service.exception.DuplicateProductException;
import com.smartcart.product_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private final  ProductRepository productRepository;
    private final ModelMapper modelMapper;
    public ProductServiceImpl(ProductRepository productRepository,ModelMapper modelMapper){
        this.productRepository=productRepository;
        this.modelMapper=modelMapper;
    }

    @Override
   public Product addProduct(Product product){
        if(productRepository
                .existsByNameIgnoreCaseAndCategoryIgnoreCase(product.getName()
                        ,product.getCategory())) {
            throw new DuplicateProductException("Product already exists with the name"
                    + product.getName() + "and category" + product.getCategory());
        }
        ProductEntity productMap= modelMapper.map(product, ProductEntity.class);
        product.setStatus(String.valueOf(ProductStatus.ACTIVE));
        return modelMapper.map(productRepository.save(productMap),Product.class);
        }

    @Override
    public Product updateProduct(Long id, Product product) {
       ProductEntity existingProduct= productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product Not Found"));
       existingProduct.setName(product.getName());
       existingProduct.setCategory(product.getCategory());
       existingProduct.setStatus(ProductStatus.valueOf(product.getStatus().toUpperCase()));
       existingProduct.setPrice(product.getPrice());
       existingProduct.setStockCount(product.getStockCount());
      return modelMapper.map(productRepository.save(existingProduct),Product.class);
    }

    @Override
    public void deleteProduct(Long id) {
       productRepository.deleteById(id);
    }
    @Override
    public void removeProduct(Long id){
        ProductEntity product=productRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Product Not Found"));
        product.setStatus(ProductStatus.DISCONTINUED);
        productRepository.save(product);
    }

    @Override
    public List<Product> searchProducts(String keyword) {
      List<ProductEntity> products=  productRepository
                .findByNameContainingIgnoreCaseAndCategoryContainingIgnoreCase(keyword,keyword);
           List<Product> resultsList= products.stream()
                    .map(product->modelMapper.map(product,Product.class))
                    .collect(Collectors.toList());
        return resultsList;
    }

    @Override
    public Product getProductById(Long id) {
       ProductEntity foundProduct= productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Product Not Found"));
        return modelMapper.map(foundProduct,Product.class);
    }
}




