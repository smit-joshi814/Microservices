package com.smit.productms.product.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.smit.productms.product.external.Category;

@FeignClient(name = "CATEGORY-MICROSERVICE")
public interface CategoryClient {
	@GetMapping("category/{id}")
	Category getCategory(@PathVariable Integer id);
}
