package com.site3.ecommerce.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class PaymentDto implements Serializable {
	 
	 
	private static final long serialVersionUID = 1L;

	
	   	private long id;
	   	private String paymentId;
	   	
	    private Date datePayment;
	    private long cardNumber;
	    private String cardType;
	    
	    private OrderDto order;
}

