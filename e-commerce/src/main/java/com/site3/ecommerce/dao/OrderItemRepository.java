package com.site3.ecommerce.dao;


import com.site3.ecommerce.entities.CategoryEntity;
import com.site3.ecommerce.entities.OrderItem;
import com.site3.ecommerce.entities.OrderItemEntity;
import com.site3.ecommerce.entities.ProductEntity;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

//@CrossOrigin("*")
//@RepositoryRestResource
@Repository
public interface OrderItemRepository extends PagingAndSortingRepository<OrderItemEntity, Long> {
	
	//OrderItemEntity findByProductsId(long Id);
	
	long countByProduct(ProductEntity product);
	
//	@Query("SELECT COUNT(i) FROM OrderItemEntity i WHERE i.product = :product")
//	int getTotalProductsCountByProduct(@Param("product") ProductEntity product);
	
	/*
	@Query("SELECT COUNT(p) FROM ProductEntity p WHERE p.category = :category")
	int getTotalProductsCountByCategory(@Param("category") CategoryEntity category);
*/
}
