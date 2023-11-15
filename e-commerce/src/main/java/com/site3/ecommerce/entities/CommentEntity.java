package com.site3.ecommerce.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @NoArgsConstructor @AllArgsConstructor @ToString
@Entity(name="comments")
public class CommentEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length=30, nullable=false)
	private String commentId;
	
	@Column(nullable=false)
	private String commentText;
	
	private Date commentDate;
	
	@ManyToOne
	@JoinColumn(name="users_id")
	private UserEntity user;
	
	@ManyToOne
	@JoinColumn(name="products_id")
	private ProductEntity product;

}
