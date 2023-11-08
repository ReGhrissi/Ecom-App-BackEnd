package com.site3.ecommerce.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.site3.ecommerce.dto.CategoryDto;
import com.site3.ecommerce.dto.ProductDto;
import com.site3.ecommerce.entities.Category;
import com.site3.ecommerce.entities.Product;


@Service
public class EcomMapperImpl {
	
//----------------------------- Category Mapper-----------------------------------
	
	public CategoryDto fromCategory(Category category)
	{
		
		CategoryDto categoryDTO = new CategoryDto();
		/*
		categoryDTO.setId(category.getId());
		categoryDTO.setName(category.getName());
		categoryDTO.setDescription(category.getDescription());
		*/
		
		// BeanUtils joue ce role
		BeanUtils.copyProperties(category, categoryDTO);
		
		return categoryDTO;
	}
	
	
	public Category fromCategoryDTO(CategoryDto categoryDTO)
	{
		
		Category category = new Category();
	
		BeanUtils.copyProperties(categoryDTO, category);
		
		return category;
	}

//---------------------------------- Product Mapper ----------------------------------
	
	public ProductDto fromProduct(Product product)
	{
		
		ProductDto productDTO = new ProductDto();
	
		BeanUtils.copyProperties(product, productDTO);
		
		return productDTO;
	}
	
	
	public Product fromProductDTO(ProductDto productDTO)
	{
		
		Product product = new Product();
	
		BeanUtils.copyProperties(productDTO, product);
		
		return product;
	}
//----------------------------------------------
	
	
}
