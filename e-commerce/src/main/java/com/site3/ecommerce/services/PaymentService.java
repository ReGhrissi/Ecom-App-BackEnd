package com.site3.ecommerce.services;

import java.util.List;

import com.site3.ecommerce.dto.PaymentDto;



public interface PaymentService {
	

	PaymentDto createOrder(PaymentDto payment);
	
	PaymentDto getPaymentByPaymentId(String paymentId);
	
	PaymentDto updatePayment(String id, PaymentDto paymentDto);
	
	void deletePayment(String paymentId);
	
	//List<PaymentDto> getPayments(int page, int limit, String search, int status);
	
	List<PaymentDto> getPayments();

}
