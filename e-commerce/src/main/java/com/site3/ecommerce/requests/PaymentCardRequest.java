package com.site3.ecommerce.requests;

import java.util.Date;

import lombok.Data;

@Data
public class PaymentCardRequest {

	private String cardNumber;
	private String cardOwner;
	private Date expirationDate;
	
}
