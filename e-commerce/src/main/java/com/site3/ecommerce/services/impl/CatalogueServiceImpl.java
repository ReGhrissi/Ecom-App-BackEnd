package com.site3.ecommerce.services.impl;
/*
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.site3.ecommerce.dao.CategoryRepository;
import com.site3.ecommerce.dao.ProductRepository;
import com.site3.ecommerce.dto.CategoryDto;
import com.site3.ecommerce.dto.ProductDto;
import com.site3.ecommerce.entities.Category;
import com.site3.ecommerce.entities.Product;
import com.site3.ecommerce.exceptions.CategoryNotFoundException;
import com.site3.ecommerce.exceptions.ProductNotFoundException;
import com.site3.ecommerce.mappers.EcomMapperImpl;
import com.site3.ecommerce.services.CatalogueService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class CatalogueServiceImpl implements CatalogueService {

	private CategoryRepository categoryRepository;
	private ProductRepository productRepository;
	private EcomMapperImpl ecomMapper;
	
//--------------------------------- Category -----------------------------------
	@Override
	public CategoryDto getCategory(Long categoryId) throws CategoryNotFoundException {
		
		Category category = categoryRepository.findById(categoryId)
							.orElseThrow(() -> new CategoryNotFoundException("Category Not found"));
		
        return ecomMapper.fromCategory(category);
	}

	@Override
	public List<CategoryDto> listCategories() {
		
        List<Category> categories = categoryRepository.findAll();
        
        List<CategoryDto> categoryDTOS = categories.stream()
                .map(category -> ecomMapper.fromCategory(category))
                .collect(Collectors.toList());
  */              
        /*
        List<CustomerDTO> customerDTOS=new ArrayList<>();
        for (Customer customer:customers){
            CustomerDTO customerDTO=dtoMapper.fromCustomer(customer);
            customerDTOS.add(customerDTO);
        }
        *
         */
	/*
        return categoryDTOS;
	}

	@Override
	public CategoryDto saveCategory(CategoryDto categoryDTO) {
		
        log.info("Saving new Category");
        
        Category category = ecomMapper.fromCategoryDTO(categoryDTO);
        
        Category savedCategory = categoryRepository.save(category);
        
        return ecomMapper.fromCategory(savedCategory);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDTO) {
		
        log.info("Updating Category");
        
        Category category = ecomMapper.fromCategoryDTO(categoryDTO);
        
        Category updatedCategory = categoryRepository.save(category);
        
        return ecomMapper.fromCategory(updatedCategory);
	}

	@Override
	public void deleteCategory(Long categoryId) {
		
		categoryRepository.deleteById(categoryId);
	}

	@Override
	public List<CategoryDto> searchCategories(String keyword) {
		
        List<Category> categories = categoryRepository.searchCategory(keyword);
        
        List<CategoryDto> categoryDTOS = categories.stream().map(cat -> ecomMapper
        					.fromCategory(cat)).collect(Collectors.toList());
        return categoryDTOS;
	}

	
//------------------------------ Product -------------------------------------
	@Override
	public ProductDto getProduct(Long productId) throws ProductNotFoundException {
		
		Product product = productRepository.findById(productId)
							.orElseThrow(() -> new ProductNotFoundException("Product Not found"));
		
        return ecomMapper.fromProduct(product);
	}

	@Override
	public List<ProductDto> listProducts() {
		
        List<Product> products = productRepository.findAll();
        
        List<ProductDto> productDTOS = products.stream()
                .map(product -> ecomMapper.fromProduct(product))
                .collect(Collectors.toList());
       
        return productDTOS;
	}

	@Override
	public ProductDto saveProduct(ProductDto productDTO) {
		
        log.info("Saving new Product");
        
        Product product = ecomMapper.fromProductDTO(productDTO);
        
        Product savedProduct = productRepository.save(product);
        
        return ecomMapper.fromProduct(savedProduct);
	}

	@Override
	public ProductDto updateProduct(ProductDto productDTO) {
		
        log.info("Updating Product");
        
        Product product = ecomMapper.fromProductDTO(productDTO);
        
        Product updatedProduct = productRepository.save(product);
        
        return ecomMapper.fromProduct(updatedProduct);
	}

	@Override
	public void deleteProduct(Long productId) {
		
		productRepository.deleteById(productId);
		
	}

	@Override
	public List<ProductDto> searchProducts(String keyword) {
		
        List<Product> products = productRepository.searchProduct(keyword);
        
        List<ProductDto> productDTOS = products.stream().map(p -> ecomMapper
        					.fromProduct(p)).collect(Collectors.toList());
        return productDTOS;
	}
	

}
*/