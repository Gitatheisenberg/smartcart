package com.smartcart.inventory_service.service;

import com.smartcart.inventory_service.dto.InventoryDto;
import com.smartcart.inventory_service.entity.InventoryEntity;
import com.smartcart.inventory_service.exception.ResourceNotFoundException;
import com.smartcart.inventory_service.repository.InventoryRepository;
import org.aspectj.apache.bcel.classfile.annotation.RuntimeInvisTypeAnnos;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements InventoryService{


    private InventoryRepository inventoryRepository;
    @Autowired
    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;

    }

    private ModelMapper modelMapper;
    public InventoryServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public InventoryDto getInventoryByProductId(Long productId) {
     InventoryEntity entity=   inventoryRepository.findByProductId(productId)
                .orElseThrow(()->new ResourceNotFoundException("Product not found with code: " + productId));
        return modelMapper.map(entity, InventoryDto.class);
    }

    @Override
    public InventoryDto updateInventory(Long productId, InventoryDto inventoryDto) {
      InventoryEntity entity=  inventoryRepository.findById(productId)
                .orElseThrow(()-> new ResourceNotFoundException("Product not found with code: " + productId));

        entity.setQuantity(inventoryDto.getQuantity());
      InventoryEntity saved= inventoryRepository.save(entity);
      return modelMapper.map(saved,InventoryDto.class);
    }
}
