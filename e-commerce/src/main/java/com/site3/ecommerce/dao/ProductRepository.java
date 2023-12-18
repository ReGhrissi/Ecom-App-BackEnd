package com.site3.ecommerce.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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
	
    @Modifying
    @Transactional
    @Query("DELETE FROM ProductEntity p WHERE p.productId = :productId")
    void deleteProductByProductId(@Param("productId") String productId);
	
 //   @Query("select p from Product p where p.name like :kw")
 //   List<Product> searchProduct(@Param("kw") String keyword);


//	@RestResource(path = "/productsByKeyword")
//	public List<Product> findByNameContains(@Param("mc") String mc); // methode de Spring Data
	
	@Query("SELECT COUNT(p) FROM ProductEntity p WHERE p.selectedProduct = true ")
	int getTotalSelectedProductsCount();
		//public List<ProductEntity> findBySelectedProductIsTrue(); // methode de Spring Data
	Page<ProductEntity> findBySelectedProductIsTrueOrderByCreationDateDesc(Pageable pageableRequest);
	
	@Query("SELECT COUNT(p) FROM ProductEntity p WHERE p.promotionProduct = true ")
	int getTotalPromotionProductsCount();
		//public List<ProductEntity> findByPromotionProductIsTrue();
	Page<ProductEntity> findByPromotionProductIsTrueOrderByCreationDateDesc(Pageable pageableRequest);

	@Query("SELECT COUNT(p) FROM ProductEntity p WHERE p.availableProduct = true ")
	int getTotalAvailableProductsCount();
		//public List<ProductEntity> findByAvailableProductIsTrue();
	Page<ProductEntity> findByAvailableProductIsTrueOrderByCreationDateDesc(Pageable pageableRequest);
	
	@Query("SELECT COUNT(p) FROM ProductEntity p WHERE p.tendancyProduct = true ")
	int getTotalTendancyProductsCount();
		//public List<ProductEntity> findByTendancyProductIsTrue();
	Page<ProductEntity> findByTendancyProductIsTrueOrderByCreationDateDesc(Pageable pageableRequest);
	
	@Query("SELECT COUNT(p) FROM ProductEntity p WHERE p.newProduct = true ")
	int getTotalNewProductsCount();
		//public List<ProductEntity> findByNewProductIsTrue();
	Page<ProductEntity> findByNewProductIsTrueOrderByCreationDateDesc(Pageable pageableRequest);
	
	@Query("SELECT COUNT(p) FROM ProductEntity p WHERE p.futurProduct = true ")
	int getTotalFuturProductsCount();
		//public List<ProductEntity> findByFuturProductIsTrue();
	Page<ProductEntity> findByFuturProductIsTrueOrderByCreationDateDesc(Pageable pageableRequest);
	
//-------------------------------------------------------------
	@Query("SELECT COUNT(p) FROM ProductEntity p WHERE p.category = :category")
	int getTotalProductsCountByCategory(@Param("category") CategoryEntity category);
		//List<ProductEntity> findByCategory(CategoryEntity currentCategory);
	Page<ProductEntity> findByCategoryOrderByCreationDateDesc(CategoryEntity currentCategory ,Pageable pageableRequest);
	
		@Query("SELECT COUNT(p) FROM ProductEntity p WHERE p.category = :category AND p.promotionProduct = true")
		int getTotalPromotionProductsCountByCategory(@Param("category") CategoryEntity category);
    Page<ProductEntity> findByCategoryAndPromotionProductIsTrueOrderByCreationDateDesc(CategoryEntity currentCategory, Pageable pageable);
    
		@Query("SELECT COUNT(p) FROM ProductEntity p WHERE p.category = :category AND p.newProduct = true")
		int getTotalNewProductsCountByCategory(@Param("category") CategoryEntity category);
    Page<ProductEntity> findByCategoryAndNewProductIsTrueOrderByCreationDateDesc(CategoryEntity currentCategory, Pageable pageable);
    
		@Query("SELECT COUNT(p) FROM ProductEntity p WHERE p.category = :category AND p.tendancyProduct = true")
		int getTotalTendancyProductsCountByCategory(@Param("category") CategoryEntity category);
    Page<ProductEntity> findByCategoryAndTendancyProductIsTrueOrderByCreationDateDesc(CategoryEntity currentCategory, Pageable pageable);
    
		@Query("SELECT COUNT(p) FROM ProductEntity p WHERE p.category = :category AND p.futurProduct = true")
		int getTotalFuturProductsCountByCategory(@Param("category") CategoryEntity category);
    Page<ProductEntity> findByCategoryAndFuturProductIsTrueOrderByCreationDateDesc(CategoryEntity currentCategory, Pageable pageable);
    
    
	ProductEntity findByProductId(String productId);

	ProductEntity findById(long Id);

	void deleteById(long id);
//-------------------------------------------------------------------------------------
	
	@Query("SELECT COUNT(p) FROM ProductEntity p")
    int getTotalProductsCount();
    
	@Query(value="SELECT * FROM products ORDER BY creation_date DESC", nativeQuery=true)
	Page<ProductEntity> findAllProducts(Pageable pageableRequest);

//----------------------------------------- pour tous les produits --------------------------
	@Query("SELECT COUNT(p) FROM ProductEntity p WHERE p.name LIKE %:search% OR p.description LIKE %:search%")
	int getTotalProductsCountByKeyword(@Param("search") String search);
	
	@Query(value="SELECT * FROM products p WHERE p.name LIKE %:search% OR p.description LIKE %:search% ORDER BY p.creation_date DESC", nativeQuery=true)
	Page<ProductEntity> findAllProductsByKeyword(Pageable pageableRequest, @Param("search") String search);
	
//----------------------------------------- pour les produits par catégorie --------------------------
	@Query("SELECT COUNT(p) FROM ProductEntity p WHERE p.category = :category AND (p.name LIKE %:search% OR p.description LIKE %:search%)")
	int getTotalProductsCountByCategoryAndKeyword(@Param("category") CategoryEntity category, @Param("search") String search);
		
	@Query(value="SELECT * FROM products p WHERE p.categories_id = :IdCategory AND (p.name LIKE %:search% OR p.description LIKE %:search%) ORDER BY p.creation_date DESC", nativeQuery=true)
	Page<ProductEntity> findAllProductsByCategoryAndKeyword(Pageable pageableRequest,@Param("IdCategory") long IdCategory, @Param("search") String search);
		

	
//	@Query("SELECT user FROM UserEntity user")
//	Page<UserEntity> findAllUsers(Pageable pageableRequest);

//	@Query(value="SELECT * FROM users u WHERE (u.first_name = ?1 OR u.last_name = ?1) AND u.email_verification_status = ?2", nativeQuery=true)
//	Page<UserEntity> findAllUserByCriteria(Pageable pageableRequest, String search, int status);
	
//	@Query(value="SELECT * FROM users u WHERE (u.first_name = :search OR u.last_name = :search) AND u.email_verification_status = :status", nativeQuery=true)
//	Page<UserEntity> findAllUserByCriteria(Pageable pageableRequest, @Param("search") String search, @Param("status") int status);
		



}
