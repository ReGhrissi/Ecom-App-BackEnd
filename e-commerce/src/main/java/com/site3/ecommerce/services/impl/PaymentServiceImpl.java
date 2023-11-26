package com.site3.ecommerce.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.site3.ecommerce.dto.PaymentDto;
import com.site3.ecommerce.services.PaymentService;


@Service
public class PaymentServiceImpl implements PaymentService {

	@Override
	public PaymentDto createOrder(PaymentDto payment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaymentDto getPaymentByPaymentId(String paymentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaymentDto updatePayment(String id, PaymentDto paymentDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePayment(String paymentId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<PaymentDto> getPayments() {
		// TODO Auto-generated method stub
		return null;
	}

}
