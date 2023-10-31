package com.site3.ecommerce.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.site3.ecommerce.entities.AddressEntity;
import com.site3.ecommerce.entities.UserEntity;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Long>{
	
    
	List<AddressEntity> findByUser(UserEntity currentUser);
	
	AddressEntity findByAddressId(String addressId);

}
