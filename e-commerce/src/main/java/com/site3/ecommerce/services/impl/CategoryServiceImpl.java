package com.site3.ecommerce.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.site3.ecommerce.dao.CategoryRepository;
import com.site3.ecommerce.dto.CategoryDto;
import com.site3.ecommerce.entities.CategoryEntity;
import com.site3.ecommerce.exceptions.CategoryException;
import com.site3.ecommerce.services.CategoryService;
import com.site3.ecommerce.shared.Utils;



@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	Utils util;
	

	@Override
	public CategoryDto createCategory(CategoryDto category) {
		
		CategoryEntity checkCategory = categoryRepository.findByName(category.getName());
		
		if(checkCategory != null) throw new RuntimeException("Category Alrady Exists !");
		
		
        ModelMapper modelMapper = new ModelMapper();
		
		CategoryEntity categoryEntity = modelMapper.map(category, CategoryEntity.class);
		
		categoryEntity.setCategoryId(util.generateStringId(32));
		//categoryEntity.setisActive(true);
		//categoryEntity.setPhotoName("unknown.jpg");
		
		CategoryEntity newCategory = categoryRepository.save(categoryEntity);
		
		CategoryDto categoryDto =  modelMapper.map(newCategory, CategoryDto.class);
		
		return categoryDto;
	}
	

	@Override
	public CategoryDto getCategoryByCategoryId(String categoryId) {

		CategoryEntity categoryEntity = categoryRepository.findByCategoryId(categoryId);
		
		if(categoryEntity == null) throw new RuntimeException("category not found"); 
		
		CategoryDto categoryDto = new CategoryDto();
		
		//BeanUtils.copyProperties(userEntity, userDto);
	
//--------------------------------
		ModelMapper modelMapper = new ModelMapper();	
		categoryDto = modelMapper.map(categoryEntity, CategoryDto.class);
		
		//usersDto.add(user);
//---------------------------------------		
		return categoryDto;
	}

	
	
	@Override
	public CategoryDto updateCategory(String categoryId, CategoryDto categoryDto) {
		
		CategoryEntity categoryEntity = categoryRepository.findByCategoryId(categoryId);
		
	    if (categoryEntity == null) 
	    {
	        throw new RuntimeException("category not found"); 
	    }

	    categoryEntity.setName(categoryDto.getName());
	    categoryEntity.setDescription(categoryDto.getDescription());
	   // categoryEntity.setActive(categoryDto.isActive());

	    
	    CategoryEntity categoryUpdated = categoryRepository.save(categoryEntity);
		
		ModelMapper modelMapper = new ModelMapper();	
	
		CategoryDto category = new CategoryDto();
		category = modelMapper.map(categoryUpdated, CategoryDto.class);
		
		return category;
	}

	@Override
	public void deleteCategory(String categoryId) {
		
		CategoryEntity categoryEntity = categoryRepository.findByCategoryId(categoryId);
		
		if(categoryEntity == null) throw new RuntimeException("category not found"); 
		
		categoryRepository.delete(categoryEntity);

	}

	@Override
	public List<CategoryDto> getCategories(int page, int limit, String search, int status) {
		
		if(page > 0) page = page - 1;
		
		List<CategoryDto> categoriesDto = new ArrayList<>();
		
		Pageable pageableRequest = PageRequest.of(page, limit);
		
		Page<CategoryEntity> categoryPage;
		
		if(search.isEmpty()) {
			categoryPage = categoryRepository.findAllCategories(pageableRequest);
		}
		else {
			
			categoryPage = categoryRepository.findAllCategoryByCriteria(pageableRequest, search, status);
		}
		
		
		List<CategoryEntity> categories = categoryPage.getContent();
		
		for(CategoryEntity categoryEntity: categories) {
			
			ModelMapper modelMapper = new ModelMapper();	
			
			CategoryDto category = modelMapper.map(categoryEntity, CategoryDto.class);
			
			categoriesDto.add(category);
		}
		
		return categoriesDto;
	}

}
