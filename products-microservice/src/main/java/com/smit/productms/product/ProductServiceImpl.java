package com.smit.productms.product;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smit.productms.product.clients.CategoryClient;
import com.smit.productms.product.dto.ProductDTO;
import com.smit.productms.product.external.Category;

@Service
class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

//	@Autowired
//	private RestTemplate restTemplate;

	@Autowired
	private CategoryClient categoryClient;

	@Override
	public ProductDTO addProduct(Product product) {
		return convertToDTO(productRepository.save(product));

	}

	@Override
	public ProductDTO getProduct(Integer productId) {
		Optional<Product> optionalProduct = productRepository.findById(productId);
		if (optionalProduct.isPresent()) {
			Product product = optionalProduct.get();
			return convertToDTO(product);
		}
		return null;
	}

	private ProductDTO convertToDTO(Product product) {
		Category category = categoryClient.getCategory(Integer.valueOf(product.getCategory()));
		return new ProductDTO(product.getProductId(), product.getProductName(), product.getQuantity(), category);
	}

	@Override
	public List<ProductDTO> getAllProducts() {
		return productRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toUnmodifiableList());
	}

	@Override
	public ProductDTO updateProduct(Product product, Integer productId) {
		Optional<Product> dbProductOpt = productRepository.findById(productId);
		dbProductOpt.ifPresent(dbProduct -> {
			if (Objects.nonNull(product.getProductName()) && !"".equalsIgnoreCase(product.getProductName())) {
				dbProduct.setProductName(product.getProductName());
			}

			if (Objects.nonNull(product.getQuantity())) {
				dbProduct.setQuantity(product.getQuantity());
			}

			if (Objects.nonNull(product.getCategory()) && !"".equalsIgnoreCase(product.getCategory())) {
				dbProduct.setCategory(product.getCategory());
			}
		});

		return convertToDTO(productRepository.save(dbProductOpt.get()));
	}

	@Override
	public Boolean removeProduct(Integer productId) {
		Optional<Product> dbProductOpt = productRepository.findById(productId);
		if (dbProductOpt.isPresent()) {
			productRepository.delete(dbProductOpt.get());
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

}
