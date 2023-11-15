package com.site3.ecommerce.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @NoArgsConstructor @AllArgsConstructor @ToString
@Entity
@Table(name="users")
public class UserEntity implements Serializable {


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable=false)
	private String userId;
	
	@Column(nullable=false)
	private String photoName;
	
	@Column(nullable=true, length=50)
	private String firstName;
	
	@Column(nullable=true, length=50)
	private String lastName;
	
	@Column(nullable=false, length=120, unique=true)
	private String email;
	
	@Column(nullable=false)
	private Boolean admin;
	
	@Column(nullable=false)
	private Boolean active;
	
	@Column(nullable=false) 
	private String encryptedPassword;
	
	@Column(nullable=true)
	private String emailVerificationToken;
	
	@Column(nullable=false)
	private Boolean emailVerificationStatus = false;
	
	@OneToMany(mappedBy="user", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private Set<AddressEntity> addresses;
	
	@OneToMany(mappedBy="user", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private List<CommentEntity> comments;
	
	@OneToOne(mappedBy="user", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private ContactEntity contact;
	
	@OneToOne(mappedBy="user", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private PaymentCardEntity paymentCard;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="users")
	private Set<GroupEntity> groups = new HashSet<>();
	
}
