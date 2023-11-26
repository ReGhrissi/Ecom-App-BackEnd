package com.site3.ecommerce.dao;



import com.site3.ecommerce.entities.ClientEntity;
import com.site3.ecommerce.entities.OrderEntity;
import com.site3.ecommerce.entities.UserEntity;
import com.site3.ecommerce.enums.OrderStatus;

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
public interface OrderRepository extends PagingAndSortingRepository<OrderEntity, Long> {
	
	List<OrderEntity> findByClient(ClientEntity currentClient);
	
	OrderEntity findByOrderId(String orderId);
	
	List<OrderEntity> findByOrderStatus(OrderStatus orderStatus);
	

}
