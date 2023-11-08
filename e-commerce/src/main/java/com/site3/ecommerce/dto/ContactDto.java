package com.site3.ecommerce.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ContactDto implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String contactId;
	
	private String country;
	private String city;
	private String street;
	private String postal;
	
	private String mobile;
	private String skype;
	
	private UserDto user;
	
}
