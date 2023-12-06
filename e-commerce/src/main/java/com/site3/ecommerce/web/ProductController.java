package com.site3.ecommerce.web;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.site3.ecommerce.dao.ProductRepository;
import com.site3.ecommerce.dto.ProductDto;
import com.site3.ecommerce.dto.UserDto;
import com.site3.ecommerce.entities.ProductEntity;
import com.site3.ecommerce.requests.ProductRequest;
import com.site3.ecommerce.responses.ProductResponse;
import com.site3.ecommerce.responses.UserResponse;
import com.site3.ecommerce.services.ProductService;



@RestController
@RequestMapping("/products")
public class ProductController {
	

	@Autowired
	ProductService productService;
	
	@Autowired
	ProductRepository productRepository;
	
//-----------------------------------------------------------------------------------------	
	@GetMapping(path="/totalProductsCount")
	public int getTotalProductsCount() {
		
		return  productService.getTotalProductsCount();
		
	}
	
//------------------------------------------------ getProductsPages() -----------------------------------
		//@GetMapping(produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
		@GetMapping()
		public ResponseEntity<List<ProductResponse>> getProductsPages(@RequestParam(value="page", defaultValue = "1") int page,
																	  @RequestParam(value="limit", defaultValue = "20") int limit) 
		{
			
			List<ProductResponse> productsResponse = new ArrayList<>();
			
			List<ProductDto> products = productService.getProducts(page, limit);
			
			for(ProductDto productDto: products) {
				
				ModelMapper modelMapper = new ModelMapper();
				ProductResponse productResponse =  modelMapper.map(productDto, ProductResponse.class);
				
				productsResponse.add(productResponse);
			}
			
			return new ResponseEntity<List<ProductResponse>>(productsResponse, HttpStatus.OK);
		}
		
//-----------------------------------------------------------------------------------------	
		@GetMapping(path="/totalProductsCountByKeyword/{kw}")
		public int getTotalProductsCountByKeyword( @PathVariable(name="kw") String keyword) {
			
			return  productService.getTotalSearchProductsCount(keyword);
			
		}
			
//------------------------------------------------ getProductsBySearch() -----------------------------------
		
		//@GetMapping(produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
		@GetMapping("/search/{kw}")
		public ResponseEntity<List<ProductResponse>> getProductsBySearch(@RequestParam(value="page", defaultValue = "1") int page,
																		 @RequestParam(value="limit", defaultValue = "20") int limit,
																		 @PathVariable(name="kw") String keyword) 
		{
					
			List<ProductResponse> productsResponse = new ArrayList<>();
					
			List<ProductDto> products = productService.getSearchProducts(page, limit, keyword);
					 
				for(ProductDto productDto: products) {
						
						ModelMapper modelMapper = new ModelMapper();
						ProductResponse productResponse =  modelMapper.map(productDto, ProductResponse.class);
						
						productsResponse.add(productResponse);
				}
					
			return new ResponseEntity<List<ProductResponse>>(productsResponse, HttpStatus.OK);
		}
	
//-------------------------------------------Get All Products----------------------------------------------
	/*
	@GetMapping
	public ResponseEntity<List<ProductResponse>>getProducts() {
		
		List<ProductDto> products = productService.getAllProducts();
		
		Type listType = new TypeToken<List<ProductResponse>>() {}.getType();
		List<ProductResponse> productsResponse = new ModelMapper().map(products, listType);
		
		return new ResponseEntity<List<ProductResponse>>(productsResponse, HttpStatus.OK);
		
	}
*/
//----------------------------------------Get One Product --------------------------------
	
	@GetMapping("/{id}")
	public  ResponseEntity<ProductResponse> getOneProduct(@PathVariable(name="id") String productId) {
		
		ProductDto productDto = productService.getProduct(productId);
		
		ModelMapper modelMapper = new ModelMapper();
		
		ProductResponse productResponse = modelMapper.map(productDto, ProductResponse.class);
		
		return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
	}
	
//-------------------------------------------Post Product ------------------------------
	
	@PostMapping("/{categoryId}")
	public ResponseEntity<ProductResponse> StoreProduct(@RequestBody ProductRequest productRequest,@PathVariable(name="categoryId") String categoryId) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		ProductDto productDto = modelMapper.map(productRequest, ProductDto.class);
		ProductDto createProduct = productService.createProduct(productDto, categoryId);
		
		ProductResponse newProduct = modelMapper.map(createProduct, ProductResponse.class);
		
		return new ResponseEntity<ProductResponse>(newProduct, HttpStatus.CREATED);
	}

//-------------------------------------- PUT Product -----------------------------
	
	@PutMapping("/{id}")
	public ResponseEntity<ProductResponse> updateProduct(@PathVariable(name="id") String productId, 
														@RequestBody ProductRequest productRequest) {
		
		
		ProductDto productDto = new ProductDto();
		
		//BeanUtils.copyProperties(userRequest, userDto);
		ModelMapper modelMapper = new ModelMapper();
		productDto = modelMapper.map(productRequest, ProductDto.class);
		
		ProductDto updateProduct = productService.updateProduct(productId, productDto);
		
		ProductResponse productResponse = new ProductResponse();
		
		//BeanUtils.copyProperties(updateUser, userResponse);
		productResponse = modelMapper.map(updateProduct, ProductResponse.class);
		
		return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.ACCEPTED);

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
	
	public byte[] getPhoto(@PathVariable("id") String id) throws Exception
	{
		ProductEntity p = productRepository.findByProductId(id);
		return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/Images_Projets/Projet_Ecommerce/Produits/"+p.getPhotoName()));
		
		
	}
	
	@PostMapping(path="/uploadPhoto/{id}")
	public  void uploadPhoto(MultipartFile file,@PathVariable String id) throws Exception
	{
		
		ProductEntity p = productRepository.findByProductId(id);
		p.setPhotoName(id+".jpg");
		Files.write(Paths.get(System.getProperty("user.home")+"/Images_Projets/Projet_Ecommerce/Produits/"+p.getPhotoName()),file.getBytes());
		productRepository.save(p);
	
	}
	
//==========================================================================================	
	@GetMapping(path="/totalProductsCountByCat/{categoryId}")
	public int getTotalProductsCountByCategory(@PathVariable String categoryId) {
					
			return  productService.getTotalProductsCountByCategory(categoryId);			
	} 
		
	@GetMapping(path="/productsByCat/{categoryId}")
	public ResponseEntity<List<ProductResponse>>getProductsByCategory(@PathVariable String categoryId, 
																	  @RequestParam(value="page", defaultValue = "1") int page,
																	  @RequestParam(value="limit", defaultValue = "20") int limit) {
		
		List<ProductDto> products = productService.getAllProductsByCategory(categoryId,page,limit);
		
		Type listType = new TypeToken<List<ProductResponse>>() {}.getType();
		List<ProductResponse> productsResponse = new ModelMapper().map(products, listType);
		
		return new ResponseEntity<List<ProductResponse>>(productsResponse, HttpStatus.OK);
		
	}
	
  /******************************************************************/
	@GetMapping(path="/totalPromotionProductsCountByCat/{categoryId}")
	public int getTotalPromotionProductsCountByCategory(@PathVariable String categoryId) {
					
			return  productService.getTotalPromotionProductsCountByCategory(categoryId);			
	} 
		
	@GetMapping(path="/promotionProductsByCat/{categoryId}")
	public ResponseEntity<List<ProductResponse>>getPromotionProductsByCategory(@PathVariable String categoryId, 
																	  @RequestParam(value="page", defaultValue = "1") int page,
																	  @RequestParam(value="limit", defaultValue = "20") int limit) {
		
		List<ProductDto> products = productService.getPromotionProductsByCategory(categoryId,page,limit);
		
		Type listType = new TypeToken<List<ProductResponse>>() {}.getType();
		List<ProductResponse> productsResponse = new ModelMapper().map(products, listType);
		
		return new ResponseEntity<List<ProductResponse>>(productsResponse, HttpStatus.OK);
		
	}

  /******************************************************************/
	@GetMapping(path="/totalNewProductsCountByCat/{categoryId}")
	public int getTotalNewProductsCountByCategory(@PathVariable String categoryId) {
						
			return  productService.getTotalNewProductsCountByCategory(categoryId);			
	} 
			
	@GetMapping(path="/newProductsByCat/{categoryId}")
	public ResponseEntity<List<ProductResponse>>getNewProductsByCategory(@PathVariable String categoryId, 
																		  @RequestParam(value="page", defaultValue = "1") int page,
																		  @RequestParam(value="limit", defaultValue = "20") int limit) {
			
		List<ProductDto> products = productService.getNewProductsByCategory(categoryId,page,limit);
			
		Type listType = new TypeToken<List<ProductResponse>>() {}.getType();
		List<ProductResponse> productsResponse = new ModelMapper().map(products, listType);
			
		return new ResponseEntity<List<ProductResponse>>(productsResponse, HttpStatus.OK);
			
	}
	
  /***********************************************************************/
	@GetMapping(path="/totalTendancyProductsCountByCat/{categoryId}")
	public int getTotalTendancyProductsCountByCategory(@PathVariable String categoryId) {
						
			return  productService.getTotalTendancyProductsCountByCategory(categoryId);			
	} 
			
	@GetMapping(path="/tendancyProductsByCat/{categoryId}")
	public ResponseEntity<List<ProductResponse>>getTendancyProductsByCategory(@PathVariable String categoryId, 
																		  @RequestParam(value="page", defaultValue = "1") int page,
																		  @RequestParam(value="limit", defaultValue = "20") int limit) {
			
		List<ProductDto> products = productService.getTendancyProductsByCategory(categoryId,page,limit);
			
		Type listType = new TypeToken<List<ProductResponse>>() {}.getType();
		List<ProductResponse> productsResponse = new ModelMapper().map(products, listType);
			
		return new ResponseEntity<List<ProductResponse>>(productsResponse, HttpStatus.OK);
			
	}
	
  /*******************************************************************/
	@GetMapping(path="/totalFuturProductsCountByCat/{categoryId}")
	public int getTotalFuturProductsCountByCategory(@PathVariable String categoryId) {
						
			return  productService.getTotalFuturProductsCountByCategory(categoryId);			
	} 
			
	@GetMapping(path="/futurProductsByCat/{categoryId}")
	public ResponseEntity<List<ProductResponse>>getFuturProductsByCategory(@PathVariable String categoryId, 
																		  @RequestParam(value="page", defaultValue = "1") int page,
																		  @RequestParam(value="limit", defaultValue = "20") int limit) {
			
		List<ProductDto> products = productService.getFuturProductsByCategory(categoryId,page,limit);
			
		Type listType = new TypeToken<List<ProductResponse>>() {}.getType();
		List<ProductResponse> productsResponse = new ModelMapper().map(products, listType);
			
		return new ResponseEntity<List<ProductResponse>>(productsResponse, HttpStatus.OK);
			
	}
	
	
//==========================================================================================	
	@GetMapping(path="/totalSelectedProductsCount")
	public int getTotalSelectedProductsCount() {
				
			return  productService.getTotalSelectedProductsCount();			
	}
	
	@GetMapping(path="/selectedProducts")
	public ResponseEntity<List<ProductResponse>>getSelectedProducts(@RequestParam(value="page", defaultValue = "1") int page,
			   														@RequestParam(value="limit", defaultValue = "20") int limit) 
	{
		
		List<ProductDto> products = productService.getAllSelectedProducts(page,limit);
		
		Type listType = new TypeToken<List<ProductResponse>>() {}.getType();
		List<ProductResponse> productsResponse = new ModelMapper().map(products, listType);
		
		return new ResponseEntity<List<ProductResponse>>(productsResponse, HttpStatus.OK);
		
	}
	
//==========================================================================================	
	@GetMapping(path="/totalPromoProductsCount")
	public int getTotalPromotionProductsCount() {
				
			return  productService.getTotalPromotionProductsCount();		
	}
	
	@GetMapping(path="/promoProducts")
	public ResponseEntity<List<ProductResponse>>getPromotionProducts(@RequestParam(value="page", defaultValue = "1") int page,
			   														 @RequestParam(value="limit", defaultValue = "20") int limit)
	{
		
		List<ProductDto> products = productService.getAllPromotionProducts(page,limit);
		
		Type listType = new TypeToken<List<ProductResponse>>() {}.getType();
		List<ProductResponse> productsResponse = new ModelMapper().map(products, listType);
		
		return new ResponseEntity<List<ProductResponse>>(productsResponse, HttpStatus.OK);
		
	}
	
//==========================================================================================	
	@GetMapping(path="/totalAvailableProductsCount")
	public int getTotalAvailableProductsCount() {
				
			return  productService.getTotalAvailableProductsCount();			
	}
	
	@GetMapping(path="/dispoProducts")
	public ResponseEntity<List<ProductResponse>>getAvailableProducts(@RequestParam(value="page", defaultValue = "1") int page,
			   														 @RequestParam(value="limit", defaultValue = "20") int limit) 
	{
		
		List<ProductDto> products = productService.getAllAvailableProducts(page,limit);
		
		Type listType = new TypeToken<List<ProductResponse>>() {}.getType();
		List<ProductResponse> productsResponse = new ModelMapper().map(products, listType);
		
		return new ResponseEntity<List<ProductResponse>>(productsResponse, HttpStatus.OK);
		
	}
	
//==========================================================================================	
	@GetMapping(path="/totalTendancyProductsCount")
	public int getTotalTendancyProductsCount() {
				
			return  productService.getTotalTendancyProductsCount();			
	}
	
	@GetMapping(path="/tendancyProducts")
	public ResponseEntity<List<ProductResponse>>getTendancyProducts(@RequestParam(value="page", defaultValue = "1") int page,
																	@RequestParam(value="limit", defaultValue = "20") int limit) 
	{
		
		List<ProductDto> products = productService.getAllTendancyProducts(page,limit);
		
		Type listType = new TypeToken<List<ProductResponse>>() {}.getType();
		List<ProductResponse> productsResponse = new ModelMapper().map(products, listType);
		
		return new ResponseEntity<List<ProductResponse>>(productsResponse, HttpStatus.OK);
		
	}
	
//=======================================================================================
	@GetMapping(path="/totalNewProductsCount")
	public int getTotalNewProductsCount() {
			
			return  productService.getTotalNewProductsCount();
			
	}
	
	@GetMapping(path="/newProducts")
	public ResponseEntity<List<ProductResponse>>getNewProducts(@RequestParam(value="page", defaultValue = "1") int page,
			  												   @RequestParam(value="limit", defaultValue = "20") int limit)
	{
		
		List<ProductDto> products = productService.getAllNewProducts(page,limit);
		
		Type listType = new TypeToken<List<ProductResponse>>() {}.getType();
		List<ProductResponse> productsResponse = new ModelMapper().map(products, listType);
		
		return new ResponseEntity<List<ProductResponse>>(productsResponse, HttpStatus.OK);		
	}
//==========================================================================================	
	@GetMapping(path="/totalFuturProductsCount")
	public int getTotalFuturProductsCount() {
			
			return  productService.getTotalFuturProductsCount();
			
	}
	
	@GetMapping(path="/futurProducts")
	public ResponseEntity<List<ProductResponse>>getFuturProducts(@RequestParam(value="page", defaultValue = "1") int page,
			   													 @RequestParam(value="limit", defaultValue = "20") int limit) 
	{
		
		List<ProductDto> products = productService.getAllFuturProducts(page,limit);
		
		Type listType = new TypeToken<List<ProductResponse>>() {}.getType();
		List<ProductResponse> productsResponse = new ModelMapper().map(products, listType);
		
		return new ResponseEntity<List<ProductResponse>>(productsResponse, HttpStatus.OK);
		
	}
	
}
