package com.site3.ecommerce.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.site3.ecommerce.dto.CategoryDTO;
import com.site3.ecommerce.dto.ProductDTO;
import com.site3.ecommerce.entities.Category;
import com.site3.ecommerce.entities.Product;


@Service
public class EcomMapperImpl {
	
//----------------------------- Category Mapper-----------------------------------
	
	public CategoryDTO fromCategory(Category category)
	{
		
		CategoryDTO categoryDTO = new CategoryDTO();
		/*
		categoryDTO.setId(category.getId());
		categoryDTO.setName(category.getName());
		categoryDTO.setDescription(category.getDescription());
		*/
		
		// BeanUtils joue ce role
		BeanUtils.copyProperties(category, categoryDTO);
		
		return categoryDTO;
	}
	
	
	public Category fromCategoryDTO(CategoryDTO categoryDTO)
	{
		
		Category category = new Category();
	
		BeanUtils.copyProperties(categoryDTO, category);
		
		return category;
	}

//---------------------------------- Product Mapper ----------------------------------
	
	public ProductDTO fromProduct(Product product)
	{
		
		ProductDTO productDTO = new ProductDTO();
	
		BeanUtils.copyProperties(product, productDTO);
		
		return productDTO;
	}
	
	
	public Product fromProductDTO(ProductDTO productDTO)
	{
		
		Product product = new Product();
	
		BeanUtils.copyProperties(productDTO, product);
		
		return product;
	}
//----------------------------------------------
	
	
}
