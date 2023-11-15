package com.site3.ecommerce.services.impl;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.site3.ecommerce.dao.CategoryRepository;
import com.site3.ecommerce.dao.ProductRepository;
import com.site3.ecommerce.dto.CategoryDto;
import com.site3.ecommerce.dto.ProductDto;
import com.site3.ecommerce.entities.CategoryEntity;
import com.site3.ecommerce.entities.ProductEntity;

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
	
//-------------------------------------------------------------------------------------------	
	@Override
	public List<ProductDto> getAllProductsByCategory(String categoryId) {
		
		CategoryEntity currentCategory = categoryRepository.findByCategoryId(categoryId);
		
		List<ProductEntity> products = productRepository.findByCategory(currentCategory);
		
		Type listType = new TypeToken<List<ProductDto>>() {}.getType();
		
		List<ProductDto> productsDto = new ModelMapper().map(products, listType);
		
		return productsDto;
	}

	@Override
	public List<ProductDto> getAllSelectedProducts() {
		
		List<ProductEntity> products = (List<ProductEntity>) productRepository.findBySelectedProductIsTrue();
		
		Type listType = new TypeToken<List<ProductDto>>() {}.getType();
		
		List<ProductDto> productsDto = new ModelMapper().map(products, listType);
		
		return productsDto;
	}
	
	@Override
	public List<ProductDto> getAllPromotionProducts() {
		
		List<ProductEntity> products = (List<ProductEntity>) productRepository.findByPromotionProductIsTrue();
		
		Type listType = new TypeToken<List<ProductDto>>() {}.getType();
		
		List<ProductDto> productsDto = new ModelMapper().map(products, listType);
		
		return productsDto;
	}

	@Override
	public List<ProductDto> getAllAvailableProducts() {
		
		List<ProductEntity> products = (List<ProductEntity>) productRepository.findByAvailableProductIsTrue();
		
		Type listType = new TypeToken<List<ProductDto>>() {}.getType();
		
		List<ProductDto> productsDto = new ModelMapper().map(products, listType);
		
		return productsDto;
	}

	@Override
	public List<ProductDto> getAllTendancyProducts() {
		
		List<ProductEntity> products = (List<ProductEntity>) productRepository.findByTendancyProductIsTrue();
		
		Type listType = new TypeToken<List<ProductDto>>() {}.getType();
		
		List<ProductDto> productsDto = new ModelMapper().map(products, listType);
		
		return productsDto;
	}

	@Override
	public List<ProductDto> getAllNewProducts() {
		
		List<ProductEntity> products = (List<ProductEntity>) productRepository.findByNewProductIsTrue();
		
		Type listType = new TypeToken<List<ProductDto>>() {}.getType();
		
		List<ProductDto> productsDto = new ModelMapper().map(products, listType);
		
		return productsDto;
	}

	@Override
	public List<ProductDto> getAllFuturProducts() {
		
		List<ProductEntity> products = (List<ProductEntity>) productRepository.findByFuturProductIsTrue();
		
		Type listType = new TypeToken<List<ProductDto>>() {}.getType();
		
		List<ProductDto> productsDto = new ModelMapper().map(products, listType);
		
		return productsDto;
	}



//--------------------------------------------------------------------------------------------	
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
	public ProductDto updateProduct(String productId, ProductDto productDto) {
		
		ProductEntity productEntity = productRepository.findByProductId(productId);
		
	    if (productEntity == null) 
	    {
	        throw new RuntimeException("ce produit n'existe pas !");
	    }
		

	    // Mettez à jour les propriétés de l'entité UserEntity
	    productEntity.setName(productDto.getName());
	    productEntity.setDescription(productDto.getDescription());
	    productEntity.setPrice(productDto.getPrice());
	    productEntity.setCurrentPrice(productDto.getCurrentPrice());
	    productEntity.setStock(productDto.getStock());
	    productEntity.setPromotionProduct(productDto.getPromotionProduct());
	    productEntity.setPromotionRate(productDto.getPromotionRate());
	    productEntity.setNewProduct(productDto.getNewProduct());
	    productEntity.setFuturProduct(productDto.getFuturProduct());
	    productEntity.setAvailableProduct(productDto.getAvailableProduct());
	    productEntity.setSelectedProduct(productDto.getSelectedProduct());
	    productEntity.setTendancyProduct(productDto.getTendancyProduct());
	    
	    ProductEntity productUpdated = productRepository.save(productEntity);
		
		ModelMapper modelMapper = new ModelMapper();	
	
		ProductDto product = new ProductDto();

		product = modelMapper.map(productUpdated, ProductDto.class);
		
		return product;
	}
	
	
	@Override
	public void deleteProduct(String productId) {
		
		ProductEntity product = productRepository.findByProductId(productId);
		
		

		if(product == null) throw new RuntimeException("Product not found !");
		
		long  ID = product.getId();
		
		//System.out.println("product :"+ product.toString());
		
		productRepository.deleteById(1);

	}






}
