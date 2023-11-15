package com.site3.ecommerce.services;

import com.site3.ecommerce.dto.PaymentCardDto;



public interface PaymentCardService {
	
	PaymentCardDto createPaymentCard(PaymentCardDto paymentCard, String email);
	
	PaymentCardDto getPaymentCard(String paymentCardId);
	
	void deletePaymentCard(String paymentCardId);

}
