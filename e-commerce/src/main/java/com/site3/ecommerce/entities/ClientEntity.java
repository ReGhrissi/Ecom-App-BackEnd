package com.site3.ecommerce.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data @NoArgsConstructor @AllArgsConstructor @ToString
@Entity(name="clients")
public class ClientEntity implements Serializable {


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
  	private long id;
	
	@NotBlank
	@Column(length = 30)
  	private String clinetId;
  	
	@Column(length=20, nullable=false)
    private String name;
	
	@Column( nullable=false)
    private String email;
	
	@Column(nullable=false)
    private String address;
	
	@Column(length=20, nullable=false) 
    private String phoneNumber;
	
	@Column(length=20, nullable=false)
    private String whatsapp;
    
//	@ManyToOne 					   //???
//	@JoinColumn(name="users_id")  //???
 //   private UserEntity user;
	
	private String username;
	
}
