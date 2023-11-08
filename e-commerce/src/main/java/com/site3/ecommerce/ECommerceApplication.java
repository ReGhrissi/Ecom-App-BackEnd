package com.site3.ecommerce;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.site3.ecommerce.dao.CategoryRepository;
import com.site3.ecommerce.dao.ProductRepository;
import com.site3.ecommerce.entities.Category;
import com.site3.ecommerce.entities.CategoryEntity;
import com.site3.ecommerce.entities.Product;
import com.site3.ecommerce.entities.ProductEntity;
import com.site3.ecommerce.shared.Utils;

import net.bytebuddy.utility.RandomString;


//@CrossOrigin("*")
@SpringBootApplication
public class ECommerceApplication  implements CommandLineRunner{
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext();
	}

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;
	
	
	@Autowired
	Utils util;
	
	
	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}

			@Override
			public void run(String... args) throws Exception {
				
				repositoryRestConfiguration.exposeIdsFor(ProductEntity.class, CategoryEntity.class);
				
				categoryRepository.save(new CategoryEntity(1, util.generateStringId(32),"unknown.jpg","Téléphone & Tablette",null, true, null));
				categoryRepository.save(new CategoryEntity(2, util.generateStringId(32),"unknown.jpg","PC & Imprimantes",null, true, null));
				categoryRepository.save(new CategoryEntity(3, util.generateStringId(32),"unknown.jpg","TV & HI Tech",null, true, null));
				categoryRepository.save(new CategoryEntity(4, util.generateStringId(32),"unknown.jpg","Appareils Photo",null, true, null));
				categoryRepository.save(new CategoryEntity(5, util.generateStringId(32),"unknown.jpg","Accessoires",null, true, null));
				categoryRepository.save(new CategoryEntity(6, util.generateStringId(32),"unknown.jpg","Jeux Vidéos",null, true, null));
				categoryRepository.save(new CategoryEntity(7, util.generateStringId(32),"unknown.jpg","Open Logiciels",null, true, null));
				
				Random rnd = new Random();
				categoryRepository.findAll().forEach
				(c->{
						for(int i=0; i<10 ; i++)
						{
							ProductEntity p = new ProductEntity();
							p.setName(RandomString.make(18));
							p.setDescription(RandomString.make(15));
							p.setCurrentPrice(10+rnd.nextInt(200));
							p.setAvailableProduct(rnd.nextBoolean());
							p.setPromotionProduct(rnd.nextBoolean());
							p.setSelectedProduct(rnd.nextBoolean());
							p.setCategory(c);
							p.setPhotoName("unknown.jpg");
							productRepository.save(p);
						}
					});		 
				}

}
