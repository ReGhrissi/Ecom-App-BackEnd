package com.site3.ecommerce.web;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.site3.ecommerce.dao.ProductRepository;
import com.site3.ecommerce.entities.Product;



@CrossOrigin("*")
@RestController
public class CatalogueRestControler {

	private ProductRepository productRepository;
	
	// injection de dependence via construcleur 
	// constructeur avec parametre -> equivalent a @Autowired (
	public CatalogueRestControler(ProductRepository productRepository)
	{
		this.productRepository =productRepository;
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
