package com.site3.ecommerce.services.impl;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.site3.ecommerce.dao.CategoryRepository;
import com.site3.ecommerce.dao.ProductRepository;
import com.site3.ecommerce.dto.AddressDto;
import com.site3.ecommerce.dto.CategoryDto;
import com.site3.ecommerce.dto.ProductDto;
import com.site3.ecommerce.dto.UserDto;
import com.site3.ecommerce.entities.AddressEntity;
import com.site3.ecommerce.entities.CategoryEntity;
import com.site3.ecommerce.entities.ProductEntity;
import com.site3.ecommerce.entities.UserEntity;
import com.site3.ecommerce.services.ProductService;
import com.site3.ecommerce.shared.Utils;


@Service
public class ProductServiceImpl implements ProductService {
	
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	Utils util;
	

	@Override
	public List<ProductDto> getAllProducts() {
		
		
		List<ProductEntity> products = (List<ProductEntity>) productRepository.findAll();
		
		Type listType = new TypeToken<List<ProductDto>>() {}.getType();
		
		List<ProductDto> productsDto = new ModelMapper().map(products, listType);
		
		return productsDto;
	}
	
	
	@Override
	public List<ProductDto> getAllProductsByCategory(String categoryId) {
		
		CategoryEntity currentCategory = categoryRepository.findByCategoryId(categoryId);
		
		List<ProductEntity> products = productRepository.findByCategory(currentCategory);
		
		Type listType = new TypeToken<List<ProductDto>>() {}.getType();
		
		List<ProductDto> productsDto = new ModelMapper().map(products, listType);
		
		return productsDto;
	}

	
	
	@Override
	public ProductDto createProduct(ProductDto product, String categoryId) {
		
		CategoryEntity currentCategory = categoryRepository.findByCategoryId(categoryId);
		
		ModelMapper modelMapper = new ModelMapper();
		CategoryDto categoryDto = modelMapper.map(currentCategory, CategoryDto.class);
		
		product.setProductId(util.generateStringId(30));
		product.setCategory(categoryDto);
		
		ProductEntity productEntity = modelMapper.map(product, ProductEntity.class); 
		
		ProductEntity newProduct = productRepository.save(productEntity);
		
		ProductDto productDto = modelMapper.map(newProduct, ProductDto.class);
		
		return productDto;
	}

	@Override
	public ProductDto getProduct(String productId) {
		
		ProductEntity productEntity = productRepository.findByProductId(productId);
		
		ModelMapper modelMapper = new ModelMapper();
		
		ProductDto productDto = modelMapper.map(productEntity, ProductDto.class);
		
		return productDto;
	}

	@Override
	public void deleteProduct(String productId) {
		
		ProductEntity product = productRepository.findByProductId(productId);
		
		if(product == null) throw new RuntimeException("Product not found");
		
		productRepository.delete(product);

	}



}
