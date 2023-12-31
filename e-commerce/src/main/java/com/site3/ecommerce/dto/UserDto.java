package com.site3.ecommerce.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class UserDto implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String userId;
	private String photoName;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Boolean admin;
	private Boolean active;
	private String encryptedPassword;
	private String emailVerificationToken;
	private Boolean emailVerificationStatus = false;
	
	private List<AddressDto> addresses;
	private List<CommentDto> comments;
	private ContactDto contact;
	private PaymentCardDto paymentCard;

}
