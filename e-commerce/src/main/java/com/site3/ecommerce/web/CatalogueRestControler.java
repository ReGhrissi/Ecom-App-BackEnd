package com.site3.ecommerce.web;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.site3.ecommerce.dao.CategoryRepository;
import com.site3.ecommerce.dao.ProductRepository;
import com.site3.ecommerce.dto.CategoryDTO;
import com.site3.ecommerce.dto.ProductDTO;
import com.site3.ecommerce.entities.Product;
import com.site3.ecommerce.exceptions.CategoryNotFoundException;
import com.site3.ecommerce.exceptions.ProductNotFoundException;
import com.site3.ecommerce.services.CatalogueService;
import com.site3.ecommerce.services.impl.CatalogueServiceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@AllArgsConstructor
//@Slf4j
@CrossOrigin("*")
@RestController
public class CatalogueRestControler {
	
	private CategoryRepository categoryRepository;
	private ProductRepository productRepository;
	
/*  
    // injection de dependence via construcleur 
	// constructeur avec parametre -> equivalent a @Autowired (
	public CatalogueRestControler(ProductRepository productRepository)
	{
		this.productRepository =productRepository;
	}
	
*/
	
	private CatalogueService catalogueService;
	
//---------------------------- Category ------------------------------------
	
    @GetMapping("/dto/categories/{id}")
    public CategoryDTO getCategory(@PathVariable(name = "id") Long categoryId) throws CategoryNotFoundException {
    
    	return catalogueService.getCategory(categoryId);
    }
    
    
	@GetMapping("/dto/categories")
    public List<CategoryDTO> Categories(){
		
        return catalogueService.listCategories();
    }
	 
    
    @PostMapping("/dto/categories")
    public CategoryDTO saveCategory(@RequestBody CategoryDTO categoryDTO){
        return catalogueService.saveCategory(categoryDTO);
    }
    
    
    @PutMapping("/dto/categories/{categoryId}")
    public CategoryDTO updateCategory(@PathVariable Long categoryId, @RequestBody CategoryDTO categoryDTO){
    	
    	categoryDTO.setId(categoryId);
    	
        return catalogueService.updateCategory(categoryDTO);
    }
    
    
    @DeleteMapping("/dto/categories/{id}")
    public void deleteCategory(@PathVariable Long id){
    	
        catalogueService.deleteCategory(id);
    }
    
	
    @GetMapping("/dto/categories/search")
    public List<CategoryDTO> searchCategories(@RequestParam(name = "keyword",defaultValue = "") String keyword){
    
    	return catalogueService.searchCategories("%"+keyword+"%");
    }
    
//--------------------------- Produit --------------------------------------
	
    @GetMapping("/dto/products/{id}")
    public ProductDTO getProduct(@PathVariable(name = "id") Long productId) throws ProductNotFoundException {
    
    	return catalogueService.getProduct(productId);
    }
    
    
	@GetMapping("/dto/products")
    public List<ProductDTO> Products(){
		
        return catalogueService.listProducts();
    }
	 
    
    @PostMapping("/dto/products")
    public ProductDTO saveProduct(@RequestBody ProductDTO productDTO){
    	
        return catalogueService.saveProduct(productDTO);
    }
    
    
    @PutMapping("/dto/products/{productId}")
    public ProductDTO updateProduct(@PathVariable Long productId, @RequestBody ProductDTO productDTO){
    	
    	productDTO.setId(productId);
    	
        return catalogueService.updateProduct(productDTO);
    }
    
    
    @DeleteMapping("/dto/products/{id}")
    public void deleteProduct(@PathVariable Long id){
    	
        catalogueService.deleteProduct(id);
    }
    
	
    @GetMapping("/dto/products/search")
    public List<ProductDTO> searchProducts(@RequestParam(name = "keyword",defaultValue = "") String keyword){
    
    	return catalogueService.searchProducts("%"+keyword+"%");
    }
    
    

	// methodes pour la recuperation des photo de produits
	
	@GetMapping(path="/photoProduct/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
	
	public byte[] getPhoto(@PathVariable("id") Long id) throws Exception
	{
		Product p = productRepository.findById(id).get();
		return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/Images_Projets/Projet_Ecommerce/Produits/"+p.getPhotoName()));
		
		
	}
	
	@PostMapping(path="/uploadPhoto/{id}")
	public  void uploadPhoto(MultipartFile file,@PathVariable Long id) throws Exception
	{
		
		Product p = productRepository.findById(id).get();
		p.setPhotoName(id+".jpg");
		Files.write(Paths.get(System.getProperty("user.home")+"/Images_Projets/Projet_Ecommerce/Produits/"+p.getPhotoName()),file.getBytes());
		productRepository.save(p);
	
	}
	
	
	
}
