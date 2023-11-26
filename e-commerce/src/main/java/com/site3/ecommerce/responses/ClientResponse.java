package com.site3.ecommerce.responses;



import lombok.Data;

@Data
public class ClientResponse {

	private String clientId;
	private String name;
    private String email;
    private String address;
    private String phoneNumber;
    private String whatsapp;
    
   // private UserResponse user;
    
    private String username;
}
