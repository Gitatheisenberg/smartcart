package com.smartcart.inventory_service.controller;

import com.smartcart.inventory_service.dto.InventoryDto;
import com.smartcart.inventory_service.service.InventoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private  InventoryService inventoryService;

    @GetMapping("/{productId}")
   public InventoryDto getInventory(@PathVariable Long productId) {
        return inventoryService.getInventoryByProductId(productId);


    }
    @PutMapping("/{productId}")
    public InventoryDto updateInventory(@PathVariable Long productId,@RequestBody InventoryDto inventoryDto) {
      return  inventoryService.updateInventory(productId, inventoryDto);
    }
}
