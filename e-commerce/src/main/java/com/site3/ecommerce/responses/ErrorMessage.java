package com.site3.ecommerce.responses;

import java.util.Date;

import lombok.Data;

@Data
public class ErrorMessage {

	private Date timestamp;
	private String message;

	
	public ErrorMessage(Date timestamp, String message) 
	{
		super();
		this.timestamp = timestamp;
		this.message = message;
	}
	
}

