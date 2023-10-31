package com.site3.ecommerce.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class AddressDto implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String addressId;
	private String city;
	private String country;
	private String street;
	private String postal;
	private String type;
	
	private UserDto user;

}
