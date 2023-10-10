package com.site3.ecommerce.web;

import java.util.Date;
import java.util.List;

import com.site3.ecommerce.entities.Client;
import com.site3.ecommerce.entities.Order;

import lombok.Data;

@Data
public class PaymentForm {

	    private Date datePayment;
	    private long cardNumber;
	    private String cardType;
	    
	    private Order order = new Order();
}
