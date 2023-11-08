package com.site3.ecommerce.responses;

import java.util.Date;

import lombok.Data;

@Data
public class PaymentCardResponse {
	
	private String paymentCardId;
	private String cardNumber;
	private String cardOwner;
	private Date expirationDate;

}
