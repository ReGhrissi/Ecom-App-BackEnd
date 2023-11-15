package com.site3.ecommerce.services;

import java.util.List;

import com.site3.ecommerce.dto.ProductDto;



public interface ProductService {

	List<ProductDto> getAllProducts();
//------	
	List<ProductDto> getAllProductsByCategory(String categoryId);
	
	List<ProductDto> getAllSelectedProducts();
	
	List<ProductDto> getAllPromotionProducts();
	
	List<ProductDto> getAllAvailableProducts();
	
	List<ProductDto> getAllTendancyProducts();
	
	List<ProductDto> getAllNewProducts();
	
	List<ProductDto> getAllFuturProducts();
//-----	
	ProductDto createProduct(ProductDto product, String categoryId);
	
	ProductDto getProduct(String productId);
	
	ProductDto updateProduct(String id, ProductDto productDto);
	
	void deleteProduct(String productId);
}
