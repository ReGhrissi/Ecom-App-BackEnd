package com.site3.paymentmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.site3.paymentmanager.dao.PayementRepository;
import com.site3.paymentmanager.entities.Payment;

import feign.OrderRestClient;
import model.Order;

@SpringBootApplication
@EnableFeignClients
public class PaymentManagerApplication implements CommandLineRunner {
	
	
	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;
	
	@Autowired
	private PayementRepository paymentRepository;
	
	//@Autowired
	//private OrderRestClient orderRestClient;

	public static void main(String[] args) {
		SpringApplication.run(PaymentManagerApplication.class, args);
	}

	
	@Override
	public void run(String... args) throws Exception {
		
		repositoryRestConfiguration.exposeIdsFor(Payment.class);
		
		
		paymentRepository.save(new Payment());
		
	}
}
