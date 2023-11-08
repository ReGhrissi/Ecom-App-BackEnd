package com.site3.ecommerce.web;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.site3.ecommerce.dao.CategoryRepository;
import com.site3.ecommerce.dto.CategoryDto;
import com.site3.ecommerce.entities.CategoryEntity;
import com.site3.ecommerce.exceptions.CategoryException;
import com.site3.ecommerce.requests.CategoryRequest;
import com.site3.ecommerce.responses.CategoryResponse;
import com.site3.ecommerce.responses.ErrorMessages;
import com.site3.ecommerce.services.CategoryService;


@CrossOrigin(origins="*")
@RestController
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	CategoryRepository categoryRepository;

	
//------------------------------------------- getCategory() ------------------------------
	
		//@GetMapping(path="/{id}", produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
		@GetMapping(path="/{id}")
		public ResponseEntity<CategoryResponse> getCategory(@PathVariable String id) {
			
			CategoryDto categoryDto = categoryService.getCategoryByCategoryId(id);
			
			CategoryResponse categoryResponse = new CategoryResponse();
			
			ModelMapper modelMapper = new ModelMapper();
			
			categoryResponse =  modelMapper.map(categoryDto, CategoryResponse.class);
				
			return new ResponseEntity<CategoryResponse>(categoryResponse, HttpStatus.OK);
		}

//------------------------------------------------ getAllCategories() -----------------------------------
		
		//@GetMapping(produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
		@GetMapping()
		public ResponseEntity<List<CategoryResponse>> getAllCategories(@RequestParam(value="page", defaultValue = "1")
		 											int page,@RequestParam(value="limit", defaultValue = "4")  
		 											int limit ,@RequestParam(value="search", defaultValue = "")
		  											String search,@RequestParam(value="status", defaultValue = "1") 
		  											int status) {
			
			List<CategoryResponse> categoriesResponse = new ArrayList<>();
			
			List<CategoryDto> categories = categoryService.getCategories(page, limit, search, status);
			
			for(CategoryDto categoryDto: categories) {
				
				ModelMapper modelMapper = new ModelMapper();
				CategoryResponse categoryResponse =  modelMapper.map(categoryDto, CategoryResponse.class);
				
				categoriesResponse.add(categoryResponse);
			}
			
			return new ResponseEntity<List<CategoryResponse>>(categoriesResponse, HttpStatus.OK);
		}

//---------------------------------------------- createCategory() ---------------------------------------
		
		//@PostMapping(consumes={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, 
		//	    	 produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
		@PostMapping()
		public ResponseEntity<CategoryResponse> createCategory(@RequestBody @Valid CategoryRequest categoryRequest) throws Exception {
			
			if(categoryRequest.getName().isEmpty()) throw new CategoryException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
			
			//UserDto userDto = new UserDto();
			//BeanUtils.copyProperties(userRequest, userDto);
			ModelMapper modelMapper = new ModelMapper();
			CategoryDto categoryDto = modelMapper.map(categoryRequest, CategoryDto.class);
			
			CategoryDto createCategory = categoryService.createCategory(categoryDto);
			
			CategoryResponse categoryResponse =  modelMapper.map(createCategory, CategoryResponse.class);
			
			return new ResponseEntity<CategoryResponse>(categoryResponse, HttpStatus.CREATED);
			
			
		}
	
//---------------------------------------- updateCategory() ----------------------------------------------

		//@PutMapping(path="/{id}", consumes={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, 
		//		 				  produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
		@PutMapping(path="/{id}")
		public ResponseEntity<CategoryResponse> updateCategory(@PathVariable String id, @RequestBody CategoryRequest categoryRequest) {
			
			CategoryDto categoryDto = new CategoryDto();
			
			//BeanUtils.copyProperties(userRequest, userDto);
			ModelMapper modelMapper = new ModelMapper();
			categoryDto = modelMapper.map(categoryRequest, CategoryDto.class);
			
			CategoryDto updateCategory = categoryService.updateCategory(id, categoryDto);
			
			CategoryResponse categoryResponse = new CategoryResponse();
			
			//BeanUtils.copyProperties(updateUser, userResponse);
			categoryResponse = modelMapper.map(updateCategory, CategoryResponse.class);
			
			return new ResponseEntity<CategoryResponse>(categoryResponse, HttpStatus.ACCEPTED);
		}
	
//---------------------------------------- deleteCategory() ------------------------------------------------
		
		@DeleteMapping(path="/{id}")
		public ResponseEntity<Object> deleteCategory(@PathVariable String id) {
			
			categoryService.deleteCategory(id);
			
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

//------------------------------------------Photo Category ---------------------------------------------------
		
		// methodes pour la recuperation des photo des Categories
		
		@GetMapping(path="/photoCategory/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
		
		public byte[] getPhoto(@PathVariable("id") String id) throws Exception
		{
			CategoryEntity c = categoryRepository.findByCategoryId(id);
			return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/Images_Projets/Projet_Ecommerce/Categories/"+c.getPhotoName()));
			
			
		}

		
		@PostMapping(path="/uploadCategory/{id}")
		public  void uploadPhoto(MultipartFile file,@PathVariable String id) throws Exception
		{
			
			CategoryEntity c = categoryRepository.findByCategoryId(id);
			
			c.setPhotoName(id+".jpg");
			
			Files.write(Paths.get(System.getProperty("user.home")+"/Images_Projets/Projet_Ecommerce/Categories/"+c.getPhotoName()),file.getBytes());
			categoryRepository.save(c);
		
		}
	
}
