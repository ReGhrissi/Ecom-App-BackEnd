package com.site3.ecommerce.services;

import java.util.List;

import com.site3.ecommerce.dto.ProductDto;



public interface ProductService {

	int getTotalProductsCount();
	List<ProductDto> getProducts(int page, int limit); // par page
	
	int getTotalSearchProductsCount(String search);
	List<ProductDto> getSearchProducts(int page, int limit, String search); // par recherche
	
	List<ProductDto> getAllProducts();
//------------	
		int getTotalProductsCountByCategory(String categoryId);
	List<ProductDto> getAllProductsByCategory(String categoryId,int page, int limit);
	
		int getTotalPromotionProductsCountByCategory(String categoryId);
	List<ProductDto> getPromotionProductsByCategory(String categoryId,int page, int limit);
	
		int getTotalNewProductsCountByCategory(String categoryId);
	List<ProductDto> getNewProductsByCategory(String categoryId,int page, int limit);
	
		int getTotalTendancyProductsCountByCategory(String categoryId);
	List<ProductDto> getTendancyProductsByCategory(String categoryId,int page, int limit);
	
		int getTotalFuturProductsCountByCategory(String categoryId);
	List<ProductDto> getFuturProductsByCategory(String categoryId,int page, int limit);
			
//-----------	
	int getTotalSelectedProductsCount();
	List<ProductDto> getAllSelectedProducts(int page, int limit);
	
	int getTotalPromotionProductsCount();
	List<ProductDto> getAllPromotionProducts(int page, int limit);
	
	int getTotalAvailableProductsCount();
	List<ProductDto> getAllAvailableProducts(int page, int limit);
	
	int getTotalTendancyProductsCount();
	List<ProductDto> getAllTendancyProducts(int page, int limit);
	
	int getTotalNewProductsCount();
	List<ProductDto> getAllNewProducts(int page, int limit); 
	
	int getTotalFuturProductsCount();
	List<ProductDto> getAllFuturProducts(int page, int limit);
//--------------	
	ProductDto createProduct(ProductDto product, String categoryId);
	
	ProductDto getProduct(String productId);
	
	ProductDto updateProduct(String id, ProductDto productDto);
	
	void deleteProduct(String productId);
}
