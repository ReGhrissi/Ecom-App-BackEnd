package com.site3.ecommerce.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.site3.ecommerce.entities.ContactEntity;
import com.site3.ecommerce.entities.UserEntity;

@Repository
public interface ContactRepository extends CrudRepository<ContactEntity, Long> {
	
	ContactEntity findByUserId(long userId);
	
	ContactEntity findByUser(UserEntity currentUser);
	
	ContactEntity findByContactId(String contactId);

}
