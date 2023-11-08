package com.site3.ecommerce.services;

import java.util.List;

import com.site3.ecommerce.dto.ProductDto;


public interface ProductService {

	List<ProductDto> getAllProducts();
//------	
	List<ProductDto> getAllProductsByCategory(String categoryId);
	
	ProductDto createProduct(ProductDto product, String categoryId);
	
	ProductDto getProduct(String productId);
	
	void deleteProduct(String productId);
}
