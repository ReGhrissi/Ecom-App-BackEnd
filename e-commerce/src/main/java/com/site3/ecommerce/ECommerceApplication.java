package com.site3.ecommerce;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.site3.ecommerce.dao.CategoryRepository;
import com.site3.ecommerce.dao.ProductRepository;
import com.site3.ecommerce.entities.Category;
import com.site3.ecommerce.entities.Product;

import net.bytebuddy.utility.RandomString;

//@CrossOrigin("*")
@SpringBootApplication
public class ECommerceApplication  implements CommandLineRunner{

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;
	
	
	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		repositoryRestConfiguration.exposeIdsFor(Product.class, Category.class);
		
		categoryRepository.save(new Category(null,"Para-Pharmacie",null,null));
		categoryRepository.save(new Category(null,"Esthetique",null,null));
		categoryRepository.save(new Category(null,"Parfums",null,null));
		categoryRepository.save(new Category(null,"Accesoires",null,null));
		categoryRepository.save(new Category(null,"Appareils",null,null));
		categoryRepository.save(new Category(null,"Cadeaux",null,null));
		categoryRepository.save(new Category(null,"Bebes",null,null));
		
		Random rnd = new Random();
		categoryRepository.findAll().forEach
		(c->{
				for(int i=0; i<10 ; i++)
				{
					Product p = new Product();
					p.setName(RandomString.make(18));
					p.setDescription(RandomString.make(15));
					p.setCurrentPrice(10+rnd.nextInt(200));
					p.setAvailable(rnd.nextBoolean());
					p.setPromotion(rnd.nextBoolean());
					p.setSelected(rnd.nextBoolean());
					p.setCategory(c);
					p.setPhotoName("unknown.jpg");
					productRepository.save(p);
				}
			});		
	}

}
