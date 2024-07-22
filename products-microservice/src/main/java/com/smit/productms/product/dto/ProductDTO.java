package com.smit.productms.product.dto;

import com.smit.productms.product.external.Category;

public record ProductDTO(Integer productId, String productName, Integer quantity, Category category) {
}
