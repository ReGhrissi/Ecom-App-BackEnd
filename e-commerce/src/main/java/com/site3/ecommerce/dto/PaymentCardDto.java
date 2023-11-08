package com.site3.ecommerce.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class PaymentCardDto implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String paymentCardId;
	private String cardNumber;
	private String cardOwner;
	private Date expirationDate;
	
	private UserDto user;
	

}
