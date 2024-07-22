package com.smit.productms.product;

import java.util.List;

import com.smit.productms.product.dto.ProductDTO;

public interface ProductService {

	ProductDTO addProduct(Product product);

	ProductDTO getProduct(Integer productId);

	List<ProductDTO> getAllProducts();

	ProductDTO updateProduct(Product product, Integer productId);

	Boolean removeProduct(Integer productId);

}
