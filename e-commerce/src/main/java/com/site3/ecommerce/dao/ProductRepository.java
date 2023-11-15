package com.site3.ecommerce.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.site3.ecommerce.entities.AddressEntity;
import com.site3.ecommerce.entities.CategoryEntity;
import com.site3.ecommerce.entities.Product;
import com.site3.ecommerce.entities.ProductEntity;
import com.site3.ecommerce.entities.UserEntity;


//@CrossOrigin("*")
//@RepositoryRestResource //spring Data Rest -> API Rest

@Repository
public interface ProductRepository extends PagingAndSortingRepository<ProductEntity, Long>{
	
    @Query("select p from Product p where p.name like :kw")
    List<Product> searchProduct(@Param("kw") String keyword);


	@RestResource(path = "/productsByKeyword")
	public List<Product> findByNameContains(@Param("mc") String mc); // methode de Spring Data
	
	public List<ProductEntity> findBySelectedProductIsTrue(); // methode de Spring Data
	
	public List<ProductEntity> findByPromotionProductIsTrue();

	public List<ProductEntity> findByAvailableProductIsTrue();
	
	public List<ProductEntity> findByTendancyProductIsTrue();
	
	public List<ProductEntity> findByNewProductIsTrue();
	
	public List<ProductEntity> findByFuturProductIsTrue();
	
//-------------------------------------------------------------
	
	List<ProductEntity> findByCategory(CategoryEntity currentCategory);
	
	ProductEntity findByProductId(String productId);


	void deleteById(long id);


}
