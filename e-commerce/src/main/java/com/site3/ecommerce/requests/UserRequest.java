package com.site3.ecommerce.requests;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


import lombok.Data;

@Data
public class UserRequest {
	
	private String photoName;
	
    @NotBlank(message="Ce champ ne doit pas etre null !")
	private String firstName;
	
	@NotNull(message="Ce champ ne doit pas etre null !")
	@Size(min=3, message="Ce champ doit avoir au moins 3 Caracteres !")
	private String lastName;
	
	@NotNull(message="Ce champ ne doit pas etre null !")
	@Email(message="ce champ doit respecter le format email !")
	private String email;
	
	@NotNull(message="Ce champ ne doit pas etre null !")
	@Size(min=8, message="mot de passe doit avoir au moins 8 caracteres !")
	@Size(max=12, message="mot de passe doit avoir au max 12 caracteres !")
	@Pattern(regexp="(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$",
			message="ce mot de passe doit avoir des lettres en Maj et Minsc et numero")
	private String password;

	//admin
	private Boolean admin;
	
	private List<AddressRequest> addresses;
	
	private ContactRequest contact;
	
	private PaymentCardRequest paymentCard;
	
}
