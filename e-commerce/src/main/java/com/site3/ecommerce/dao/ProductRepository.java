package com.site3.ecommerce.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.site3.ecommerce.entities.Product;


@CrossOrigin("*")
@RepositoryRestResource //spring Data Rest -> API Rest
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	@RestResource(path = "/selectedProducts")
	public List<Product> findBySelectedIsTrue(); // methode de Spring Data
	
	 // @Query(" selact p from Product p where p.name like :x")
	//  public List<Product> Chercher(@Param("x") String mc);  -> Methode normale
	
	@RestResource(path = "/productsByKeyword")
	public List<Product> findByNameContains(@Param("mc") String mc); // methode de Spring Data
	
	
	@RestResource(path = "/promoProducts")
	public List<Product> findByPromotionIsTrue();
	
	@RestResource(path = "/dispoProducts")
	public List<Product> findByAvailableIsTrue();
	


}
