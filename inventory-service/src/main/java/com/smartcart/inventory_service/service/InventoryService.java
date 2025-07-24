package com.smartcart.inventory_service.service;

import com.smartcart.inventory_service.dto.InventoryDto;

public interface InventoryService {



    InventoryDto getInventoryByProductId(Long productId);


    InventoryDto updateInventory(Long productId,InventoryDto inventoryDto);
}
