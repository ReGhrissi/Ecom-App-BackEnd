package com.site3.ecommerce.services.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.site3.ecommerce.dao.CategoryRepository;
import com.site3.ecommerce.dao.ProductRepository;
import com.site3.ecommerce.dto.CategoryDto;
import com.site3.ecommerce.dto.ProductDto;
import com.site3.ecommerce.dto.UserDto;
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
	
//-------------------------------------------------------------------------------	
	@Override
	public List<ProductDto> getAllProducts() {
		
		
		List<ProductEntity> products = (List<ProductEntity>) productRepository.findAll();
		
		Type listType = new TypeToken<List<ProductDto>>() {}.getType();
		
		List<ProductDto> productsDto = new ModelMapper().map(products, listType);
		
		return productsDto;
	}
//-------------------------------------------------------------------------------------	
	@Override
	public int getTotalProductsCount() {
		
		return productRepository.getTotalProductsCount();
	}

//-----------------------------------------------------------------------------------------	
	@Override
	public List<ProductDto> getProducts(int page, int limit) {
		
		if(page > 0) page = page - 1;
		
		List<ProductDto> productsDto = new ArrayList<>();
		
		Pageable pageableRequest = PageRequest.of(page, limit);
		
		Page<ProductEntity> productPage;
	/*	
		if(search.isEmpty()) {
			userPage = userRepository.findAllUsers(pageableRequest);
		}
		else {
			
			userPage = userRepository.findAllUserByCriteria(pageableRequest, search, status);
		}
		*/
		productPage = productRepository.findAllProducts(pageableRequest);
		
		List<ProductEntity> products = productPage.getContent();
		
		for(ProductEntity productEntity: products) {
			
			ModelMapper modelMapper = new ModelMapper();	
			ProductDto product = modelMapper.map(productEntity, ProductDto.class);
			
			productsDto.add(product);
		}
		
		return productsDto;
	}
	
//----------------------------------------------------------------------------------	
	@Override
	public int getTotalSearchProductsCount(String search) {
		
		return productRepository.getTotalProductsCountByKeyword(search);
	}
//--------------------------------------------------------------------------------------
	@Override
	public List<ProductDto> getSearchProducts(int page, int limit, String search) {
		
		if(page > 0) page = page - 1;
		
		List<ProductDto> productsDto = new ArrayList<>();
		
		Pageable pageableRequest = PageRequest.of(page, limit);
		
		Page<ProductEntity> productPage;
	/*	
		if(search.isEmpty()) {
			userPage = userRepository.findAllUsers(pageableRequest);
		}
		else {
			
			userPage = userRepository.findAllUserByCriteria(pageableRequest, search, status);
		}
	*/
		productPage = productRepository.findAllProductsByKeyword(pageableRequest, search);
		
		List<ProductEntity> products = productPage.getContent();
		
		for(ProductEntity productEntity: products) {
			
			ModelMapper modelMapper = new ModelMapper();	
			ProductDto product = modelMapper.map(productEntity, ProductDto.class);
			
			productsDto.add(product);
		}
		
		return productsDto;
	}

//--------------------------------------------------------------------------------------

	
	@Override
	public int getTotalSearchProductsByCategoryCount(String categoryId, String search) {

		CategoryEntity currentCategory = categoryRepository.findByCategoryId(categoryId);
		
		return productRepository.getTotalProductsCountByCategoryAndKeyword(currentCategory, search);
	}

	@Override
	public List<ProductDto> getSearchProductsByCategory(int page, int limit, String categoryId, String search) {
		
		CategoryEntity currentCategory = categoryRepository.findByCategoryId(categoryId);
		
		if(page > 0) page = page - 1;
		
		List<ProductDto> productsDto = new ArrayList<>();
		
		Pageable pageableRequest = PageRequest.of(page, limit);
		
		Page<ProductEntity> productPage;
	/*	
		if(search.isEmpty()) {
			userPage = userRepository.findAllUsers(pageableRequest);
		}
		else {
			
			userPage = userRepository.findAllUserByCriteria(pageableRequest, search, status);
		}
	*/
		productPage = productRepository.findAllProductsByCategoryAndKeyword(pageableRequest, currentCategory.getId(), search);
		
		List<ProductEntity> products = productPage.getContent();
		
		for(ProductEntity productEntity: products) {
			
			ModelMapper modelMapper = new ModelMapper();	
			ProductDto product = modelMapper.map(productEntity, ProductDto.class);
			
			productsDto.add(product);
		}
		
		return productsDto;
	}

//===========================================================================================
	@Override
	public int getTotalProductsCountByCategory(String categoryId) {
		
		CategoryEntity currentCategory = categoryRepository.findByCategoryId(categoryId);
		
		return productRepository.getTotalProductsCountByCategory(currentCategory);		
	}
	
	@Override
	public List<ProductDto> getAllProductsByCategory(String categoryId, int page, int limit) {
		/*
		CategoryEntity currentCategory = categoryRepository.findByCategoryId(categoryId);
		
		List<ProductEntity> products = productRepository.findByCategory(currentCategory);
		
		Type listType = new TypeToken<List<ProductDto>>() {}.getType();
		
		List<ProductDto> productsDto = new ModelMapper().map(products, listType);
		
		return productsDto;
		*/
		CategoryEntity currentCategory = categoryRepository.findByCategoryId(categoryId);
		
		if(page > 0) page = page - 1;
		
		List<ProductDto> productsDto = new ArrayList<>();
		
		Pageable pageableRequest = PageRequest.of(page, limit);
		
		Page<ProductEntity> productPage;

		productPage = productRepository.findByCategoryOrderByCreationDateDesc(currentCategory, pageableRequest);
		
		List<ProductEntity> products = productPage.getContent();
		
		for(ProductEntity productEntity: products) {
			
			ModelMapper modelMapper = new ModelMapper();	
			ProductDto product = modelMapper.map(productEntity, ProductDto.class);
			
			productsDto.add(product);
		}
		
		return productsDto;
		
	}
	
  /************************************************************************/
	@Override
	public int getTotalPromotionProductsCountByCategory(String categoryId) {
		
		CategoryEntity currentCategory = categoryRepository.findByCategoryId(categoryId);
		
		return productRepository.getTotalPromotionProductsCountByCategory(currentCategory);
	}

	@Override
	public List<ProductDto> getPromotionProductsByCategory(String categoryId, int page, int limit) {
		
		CategoryEntity currentCategory = categoryRepository.findByCategoryId(categoryId);
		
		if(page > 0) page = page - 1;
		
		List<ProductDto> productsDto = new ArrayList<>();
		
		Pageable pageableRequest = PageRequest.of(page, limit);
		
		Page<ProductEntity> productPage;

		productPage = productRepository.findByCategoryAndPromotionProductIsTrueOrderByCreationDateDesc(currentCategory, pageableRequest);
		
		List<ProductEntity> products = productPage.getContent();
		
		for(ProductEntity productEntity: products) {
			
			ModelMapper modelMapper = new ModelMapper();	
			ProductDto product = modelMapper.map(productEntity, ProductDto.class);
			
			productsDto.add(product);
		}
		
		return productsDto;
		
	}
	
  /*************************************************************************/
	@Override
	public int getTotalNewProductsCountByCategory(String categoryId) {
		
		CategoryEntity currentCategory = categoryRepository.findByCategoryId(categoryId);
		
		return productRepository.getTotalNewProductsCountByCategory(currentCategory);
	}

	@Override
	public List<ProductDto> getNewProductsByCategory(String categoryId, int page, int limit) {

		CategoryEntity currentCategory = categoryRepository.findByCategoryId(categoryId);
		
		if(page > 0) page = page - 1;
		
		List<ProductDto> productsDto = new ArrayList<>();
		
		Pageable pageableRequest = PageRequest.of(page, limit);
		
		Page<ProductEntity> productPage;

		productPage = productRepository.findByCategoryAndNewProductIsTrueOrderByCreationDateDesc(currentCategory, pageableRequest);
		
		List<ProductEntity> products = productPage.getContent();
		
		for(ProductEntity productEntity: products) {
			
			ModelMapper modelMapper = new ModelMapper();	
			ProductDto product = modelMapper.map(productEntity, ProductDto.class);
			
			productsDto.add(product);
		}
		
		return productsDto;
		
	}
	
  /******************************************************************************/
	@Override
	public int getTotalTendancyProductsCountByCategory(String categoryId) {
		
		CategoryEntity currentCategory = categoryRepository.findByCategoryId(categoryId);
		
		return productRepository.getTotalTendancyProductsCountByCategory(currentCategory);
	}

	@Override
	public List<ProductDto> getTendancyProductsByCategory(String categoryId, int page, int limit) {

		CategoryEntity currentCategory = categoryRepository.findByCategoryId(categoryId);
		
		if(page > 0) page = page - 1;
		
		List<ProductDto> productsDto = new ArrayList<>();
		
		Pageable pageableRequest = PageRequest.of(page, limit);
		
		Page<ProductEntity> productPage;

		productPage = productRepository.findByCategoryAndTendancyProductIsTrueOrderByCreationDateDesc(currentCategory, pageableRequest);
		
		List<ProductEntity> products = productPage.getContent();
		
		for(ProductEntity productEntity: products) {
			
			ModelMapper modelMapper = new ModelMapper();	
			ProductDto product = modelMapper.map(productEntity, ProductDto.class);
			
			productsDto.add(product);
		}
		
		return productsDto;
		
	}
	
  /******************************************************************************/
	@Override
	public int getTotalFuturProductsCountByCategory(String categoryId) {
		
		CategoryEntity currentCategory = categoryRepository.findByCategoryId(categoryId);
		
		return productRepository.getTotalFuturProductsCountByCategory(currentCategory);
	}

	@Override
	public List<ProductDto> getFuturProductsByCategory(String categoryId, int page, int limit) {

		CategoryEntity currentCategory = categoryRepository.findByCategoryId(categoryId);
		
		if(page > 0) page = page - 1;
		
		List<ProductDto> productsDto = new ArrayList<>();
		
		Pageable pageableRequest = PageRequest.of(page, limit);
		
		Page<ProductEntity> productPage;

		productPage = productRepository.findByCategoryAndFuturProductIsTrueOrderByCreationDateDesc(currentCategory, pageableRequest);
		
		List<ProductEntity> products = productPage.getContent();
		
		for(ProductEntity productEntity: products) {
			
			ModelMapper modelMapper = new ModelMapper();	
			ProductDto product = modelMapper.map(productEntity, ProductDto.class);
			
			productsDto.add(product);
		}
		
		return productsDto;
		
	}

	
//===============================================================================================
	@Override
	public int getTotalSelectedProductsCount() {
		return productRepository.getTotalSelectedProductsCount();
	}
			
	@Override
	public List<ProductDto> getAllSelectedProducts(int page, int limit) {
		/*
		List<ProductEntity> products = (List<ProductEntity>) productRepository.findBySelectedProductIsTrue();
		
		Type listType = new TypeToken<List<ProductDto>>() {}.getType();
		
		List<ProductDto> productsDto = new ModelMapper().map(products, listType);
		
		return productsDto;
		*/
		
		if(page > 0) page = page - 1;
		
		List<ProductDto> productsDto = new ArrayList<>();
		
		Pageable pageableRequest = PageRequest.of(page, limit);
		
		Page<ProductEntity> productPage;

		productPage = productRepository.findBySelectedProductIsTrueOrderByCreationDateDesc(pageableRequest);
		
		List<ProductEntity> products = productPage.getContent();
		
		for(ProductEntity productEntity: products) {
			
			ModelMapper modelMapper = new ModelMapper();	
			ProductDto product = modelMapper.map(productEntity, ProductDto.class);
			
			productsDto.add(product);
		}
		
		return productsDto;
	}
	
//===============================================================================================
	@Override
	public int getTotalPromotionProductsCount() {
		return productRepository.getTotalPromotionProductsCount();
	}
		
	@Override
	public List<ProductDto> getAllPromotionProducts(int page, int limit) {
		/*
		List<ProductEntity> products = (List<ProductEntity>) productRepository.findByPromotionProductIsTrue();
		
		Type listType = new TypeToken<List<ProductDto>>() {}.getType();
		
		List<ProductDto> productsDto = new ModelMapper().map(products, listType);
		
		return productsDto;
		*/
		
		if(page > 0) page = page - 1;
		
		List<ProductDto> productsDto = new ArrayList<>();
		
		Pageable pageableRequest = PageRequest.of(page, limit);
		
		Page<ProductEntity> productPage;

		productPage = productRepository.findByPromotionProductIsTrueOrderByCreationDateDesc(pageableRequest);
		
		List<ProductEntity> products = productPage.getContent();
		
		for(ProductEntity productEntity: products) {
			
			ModelMapper modelMapper = new ModelMapper();	
			ProductDto product = modelMapper.map(productEntity, ProductDto.class);
			
			productsDto.add(product);
		}
		
		return productsDto;
	}


//===============================================================================================
	@Override
	public int getTotalAvailableProductsCount() {
		return productRepository.getTotalAvailableProductsCount();
	}
		
	@Override
	public List<ProductDto> getAllAvailableProducts(int page, int limit) {
		/*
		List<ProductEntity> products = (List<ProductEntity>) productRepository.findByAvailableProductIsTrue();
		
		Type listType = new TypeToken<List<ProductDto>>() {}.getType();
		
		List<ProductDto> productsDto = new ModelMapper().map(products, listType);
		
		return productsDto;
		*/
		
		if(page > 0) page = page - 1;
		
		List<ProductDto> productsDto = new ArrayList<>();
		
		Pageable pageableRequest = PageRequest.of(page, limit);
		
		Page<ProductEntity> productPage;

		productPage = productRepository.findByAvailableProductIsTrueOrderByCreationDateDesc(pageableRequest);
		
		List<ProductEntity> products = productPage.getContent();
		
		for(ProductEntity productEntity: products) {
			
			ModelMapper modelMapper = new ModelMapper();	
			ProductDto product = modelMapper.map(productEntity, ProductDto.class);
			
			productsDto.add(product);
		}
		
		return productsDto;
	}

//===============================================================================================
	@Override
	public int getTotalTendancyProductsCount() {
		return productRepository.getTotalTendancyProductsCount();
	}
			
	@Override
	public List<ProductDto> getAllTendancyProducts(int page, int limit) {
		/*
		List<ProductEntity> products = (List<ProductEntity>) productRepository.findByTendancyProductIsTrue();
		
		Type listType = new TypeToken<List<ProductDto>>() {}.getType();
		
		List<ProductDto> productsDto = new ModelMapper().map(products, listType);
		
		return productsDto;
		*/
		
		if(page > 0) page = page - 1;
		
		List<ProductDto> productsDto = new ArrayList<>();
		
		Pageable pageableRequest = PageRequest.of(page, limit);
		
		Page<ProductEntity> productPage;

		productPage = productRepository.findByTendancyProductIsTrueOrderByCreationDateDesc(pageableRequest);
		
		List<ProductEntity> products = productPage.getContent();
		
		for(ProductEntity productEntity: products) {
			
			ModelMapper modelMapper = new ModelMapper();	
			ProductDto product = modelMapper.map(productEntity, ProductDto.class);
			
			productsDto.add(product);
		}
		
		return productsDto;
	}
//==================================================================================================
	@Override
	public int getTotalNewProductsCount() {
		return productRepository.getTotalNewProductsCount();
	}
	
	@Override
	public List<ProductDto> getAllNewProducts(int page, int limit) {
		
		/*
		List<ProductEntity> products = (List<ProductEntity>) productRepository.findByNewProductIsTrue();
		
		Type listType = new TypeToken<List<ProductDto>>() {}.getType();
		
		List<ProductDto> productsDto = new ModelMapper().map(products, listType);
		
		return productsDto;
		*/
		
		if(page > 0) page = page - 1;
		
		List<ProductDto> productsDto = new ArrayList<>();
		
		Pageable pageableRequest = PageRequest.of(page, limit);
		
		Page<ProductEntity> productPage;

		productPage = productRepository.findByNewProductIsTrueOrderByCreationDateDesc(pageableRequest);
		
		List<ProductEntity> products = productPage.getContent();
		
		for(ProductEntity productEntity: products) {
			
			ModelMapper modelMapper = new ModelMapper();	
			ProductDto product = modelMapper.map(productEntity, ProductDto.class);
			
			productsDto.add(product);
		}
		
		return productsDto;
		
	}
//===============================================================================================
	@Override
	public int getTotalFuturProductsCount() {
		return productRepository.getTotalFuturProductsCount();
	}
	
	
	@Override
	public List<ProductDto> getAllFuturProducts(int page, int limit) {
		/*
		List<ProductEntity> products = (List<ProductEntity>) productRepository.findByFuturProductIsTrue();
		
		Type listType = new TypeToken<List<ProductDto>>() {}.getType();
		
		List<ProductDto> productsDto = new ModelMapper().map(products, listType);
		
		return productsDto;
		*/
		
		if(page > 0) page = page - 1;
		
		List<ProductDto> productsDto = new ArrayList<>();
		
		Pageable pageableRequest = PageRequest.of(page, limit);
		
		Page<ProductEntity> productPage;

		productPage = productRepository.findByFuturProductIsTrueOrderByCreationDateDesc(pageableRequest);
		
		List<ProductEntity> products = productPage.getContent();
		
		for(ProductEntity productEntity: products) {
			
			ModelMapper modelMapper = new ModelMapper();	
			ProductDto product = modelMapper.map(productEntity, ProductDto.class);
			
			productsDto.add(product);
		}
		
		return productsDto;
		
	}



//--------------------------------------------------------------------------------------------	
	@Override
	public ProductDto createProduct(ProductDto product, String categoryId) {
		
		CategoryEntity currentCategory = categoryRepository.findByCategoryId(categoryId);
		
		ModelMapper modelMapper = new ModelMapper();
		CategoryDto categoryDto = modelMapper.map(currentCategory, CategoryDto.class);
		
		product.setProductId(util.generateStringId(30));
		product.setCreationDate(new Date());
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
	    productEntity.setDetails(productDto.getDetails());
	    productEntity.setPrice(productDto.getPrice());
	    if(productDto.getPromotionProduct() == false)
	    {
	    	productEntity.setCurrentPrice(productDto.getPrice());
	    }
	    else 
	    {
	    	productEntity.setCurrentPrice(productDto.getPrice()* (1 - productDto.getPromotionRate()));	
	    }
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
	@Transactional
	public void deleteProduct(String productId) {
		
		ProductEntity product = productRepository.findByProductId(productId);
		

		if(product == null) throw new RuntimeException("Product not found !");
	/*	
		long  ID = product.getId();
		
		//System.out.println("product :"+ product.toString());
		
		productRepository.deleteById(1);
*/
		 productRepository.deleteProductByProductId(productId);
	}







}
