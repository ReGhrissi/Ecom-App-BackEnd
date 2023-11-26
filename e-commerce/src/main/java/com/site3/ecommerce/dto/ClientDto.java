package com.site3.ecommerce.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ClientDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	  	private long id;
	  	private String clientId;
	  	
	    private String name;
	    private String email;
	    private String address;
	    private String phoneNumber;
	    private String whatsapp;
	    
	   // private UserDto user;
	    
	    private String username;
}
