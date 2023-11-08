package com.site3.ecommerce.web;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.site3.ecommerce.dao.ProductRepository;
import com.site3.ecommerce.dto.ProductDto;
import com.site3.ecommerce.entities.ProductEntity;
import com.site3.ecommerce.requests.ProductRequest;
import com.site3.ecommerce.responses.ProductResponse;
import com.site3.ecommerce.services.ProductService;



@RestController
@RequestMapping("/products")
public class ProductController {
	

	@Autowired
	ProductService productService;
	
	@Autowired
	ProductRepository productRepository;
	
//-------------------------------------------Get All Products----------------------------------------------
	
	@GetMapping
	public ResponseEntity<List<ProductResponse>>getProducts() {
		
		List<ProductDto> products = productService.getAllProducts();
		
		Type listType = new TypeToken<List<ProductResponse>>() {}.getType();
		List<ProductResponse> productsResponse = new ModelMapper().map(products, listType);
		
		return new ResponseEntity<List<ProductResponse>>(productsResponse, HttpStatus.OK);
		
	}

//----------------------------------------Get One Product --------------------------------
	
	@GetMapping("/{id}")
	public  ResponseEntity<ProductResponse> getOneProduct(@PathVariable(name="id") String productId) {
		
		ProductDto productDto = productService.getProduct(productId);
		
		ModelMapper modelMapper = new ModelMapper();
		
		ProductResponse productResponse = modelMapper.map(productDto, ProductResponse.class);
		
		return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
	}
	
//-------------------------------------------Post Product ------------------------------
	
	@PostMapping(
			consumes={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE}, 
		    produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
			)
	public ResponseEntity<ProductResponse> StoreProduct(@RequestBody ProductRequest productRequest, String categoryId) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		ProductDto productDto = modelMapper.map(productRequest, ProductDto.class);
		ProductDto createProduct = productService.createProduct(productDto, categoryId);
		
		ProductResponse newProduct = modelMapper.map(createProduct, ProductResponse.class);
		
		return new ResponseEntity<ProductResponse>(newProduct, HttpStatus.CREATED);
	}

//-------------------------------------- PUT Product -----------------------------
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateProduct(@PathVariable(name="id") String productId) {
		return new ResponseEntity<>("update Product", HttpStatus.ACCEPTED);
	}

//--------------------------------------DELETE Product ------------------------------
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable(name="id") String productId) {
		
		productService.deleteProduct(productId);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

//----------------------------------------Photo Product --------------------------------
	// methodes pour la recuperation des photo de produits
	
	@GetMapping(path="/photoProduct/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
	
	public byte[] getPhoto(@PathVariable("id") Long id) throws Exception
	{
		ProductEntity p = productRepository.findById(id).get();
		return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/Images_Projets/Projet_Ecommerce/Produits/"+p.getPhotoName()));
		
		
	}
	
	@PostMapping(path="/uploadPhoto/{id}")
	public  void uploadPhoto(MultipartFile file,@PathVariable Long id) throws Exception
	{
		
		ProductEntity p = productRepository.findById(id).get();
		p.setPhotoName(id+".jpg");
		Files.write(Paths.get(System.getProperty("user.home")+"/Images_Projets/Projet_Ecommerce/Produits/"+p.getPhotoName()),file.getBytes());
		productRepository.save(p);
	
	}
	
	
}
