package com.site3.ecommerce.services;

import java.util.List;

import com.site3.ecommerce.dto.CategoryDto;



public interface CategoryService {

	CategoryDto createCategory(CategoryDto category);
	
	CategoryDto getCategoryByCategoryId(String categoryId);
	
	CategoryDto updateCategory(String id, CategoryDto categoryDto);
	
	void deleteCategory(String categoryId);
	
	//List<CategoryDto> getCategories(int page, int limit, String search, int status);
	
	List<CategoryDto> getCategories();
}
