package com.site3.ecommerce.requests;

import lombok.Data;

@Data
public class AddressRequest {
	
	private String city;
	private String country;
	private String street;
	private String postal;
	private String type;

}
