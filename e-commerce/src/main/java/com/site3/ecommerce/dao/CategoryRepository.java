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
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.site3.ecommerce.entities.Category;
import com.site3.ecommerce.entities.CategoryEntity;



//@CrossOrigin("*")
//@RepositoryRestResource //spring Data Rest -> API Rest

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<CategoryEntity, Long>{
	
    @Query("select c from Category c where c.name like :kw")
    List<Category> searchCategory(@Param("kw") String keyword);

    
	CategoryEntity findByName(String name);
	
	CategoryEntity findByCategoryId(String categoryId);
	
    @Modifying
    @Transactional
    @Query("DELETE FROM CategoryEntity c WHERE c.categoryId = :categoryId")
    void deleteCategoryByCategoryId(@Param("categoryId") String categoryId);
	
//	@Query(value="SELECT * FROM users", nativeQuery=true)
//	Page<UserEntity> findAllUsers(Pageable pageableRequest);
	
/*	
	@Query("SELECT category FROM CategoryEntity Category")
	Page<CategoryEntity> findAllCategories(Pageable pageableRequest);

//	@Query(value="SELECT * FROM users u WHERE (u.first_name = ?1 OR u.last_name = ?1) AND u.email_verification_status = ?2", nativeQuery=true)
//	Page<UserEntity> findAllUserByCriteria(Pageable pageableRequest, String search, int status);
	
//	@Query(value="SELECT * FROM users u WHERE (u.first_name = :search OR u.last_name = :search) AND u.email_verification_status = :status", nativeQuery=true)
//	Page<UserEntity> findAllUserByCriteria(Pageable pageableRequest, @Param("search") String search, @Param("status") int status);
		
	@Query(value="SELECT * FROM categories c WHERE (c.name LIKE %:search% OR c.description LIKE %:search%)", nativeQuery=true)
	Page<CategoryEntity> findAllCategoryByCriteria(Pageable pageableRequest, @Param("search") String search, @Param("status") int status);
*/

}
