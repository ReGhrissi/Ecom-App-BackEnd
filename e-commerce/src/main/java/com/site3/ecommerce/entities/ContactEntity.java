package com.site3.ecommerce.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @NoArgsConstructor @AllArgsConstructor @ToString
@Entity(name="contacts")
public class ContactEntity implements Serializable {


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;

	@NotBlank
	@Column(length = 30)
	private String contactId;

	@NotBlank
	private String mobile;
	private String skype;
	
	@OneToOne
	@JoinColumn(name="users_id")
	private UserEntity user;

}
