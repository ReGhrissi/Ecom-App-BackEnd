package com.site3.clientsorders.service.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
public class Payment {
	
	 	private Long id;
	    private Date datePayment;
	    private long cardNumber;
	    private String cardType;

}

