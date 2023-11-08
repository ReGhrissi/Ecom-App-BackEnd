package com.site3.ecommerce.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.site3.ecommerce.entities.PaymentCardEntity;
import com.site3.ecommerce.entities.UserEntity;


@Repository
public interface PaymentCardRepository extends PagingAndSortingRepository<PaymentCardEntity, Long> {

	PaymentCardEntity findByUserId(long userId);
	
	PaymentCardEntity findByUser(UserEntity currentUser);
	
	PaymentCardEntity findByPaymentCardId(String paymentCardId);
}
