package com.site3.ecommerce.dao;

import com.site3.ecommerce.entities.Client;
import com.site3.ecommerce.entities.ClientEntity;
import com.site3.ecommerce.entities.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

//@CrossOrigin("*")
//@RepositoryRestResource
@Repository
public interface ClientRepository extends PagingAndSortingRepository<ClientEntity, Long> {
	
	ClientEntity findByEmail(String email);
	
	List<ClientEntity> findByUsername(String userId);

}
