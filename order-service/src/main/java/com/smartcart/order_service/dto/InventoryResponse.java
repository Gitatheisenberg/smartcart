package com.smartcart.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class InventoryResponse {
        private String productCode;
        private boolean inStock;
    }

