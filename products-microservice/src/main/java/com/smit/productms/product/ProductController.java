package com.smit.productms.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smit.productms.product.dto.ProductDTO;

@RestController
@RequestMapping("/product")
class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	ResponseEntity<List<ProductDTO>> getAllProducts() {
		return ResponseEntity.ok(productService.getAllProducts());
	}

	@GetMapping("{id}")
	ResponseEntity<ProductDTO> getProductById(@PathVariable Integer id) {
		return ResponseEntity.ok(productService.getProduct(id));
	}

	@PostMapping
	ResponseEntity<ProductDTO> addProduct(@RequestBody Product product) {
		return ResponseEntity.ok(productService.addProduct(product));
	}

	@PutMapping("{id}")
	ResponseEntity<ProductDTO> updateProduct(@RequestBody Product product, @PathVariable Integer id) {
		return ResponseEntity.ok(productService.updateProduct(product, id));
	}

	@DeleteMapping("{id}")
	ResponseEntity<Boolean> deleteProduct(@PathVariable Integer id) {
		return ResponseEntity.ok(productService.removeProduct(id));
	}
}
