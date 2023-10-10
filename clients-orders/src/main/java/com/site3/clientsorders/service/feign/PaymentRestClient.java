package com.site3.clientsorders.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.site3.clientsorders.service.model.Payment;

@FeignClient(name ="PAYMENT-MANAGER")
public interface PaymentRestClient {
	
	@GetMapping(path="/payments/{id}")
	Payment getPaymentById(@PathVariable(name="id") Long id);

}
