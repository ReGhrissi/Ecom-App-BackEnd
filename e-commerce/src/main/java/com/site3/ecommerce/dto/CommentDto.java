package com.site3.ecommerce.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

import com.site3.ecommerce.entities.Product;

import lombok.Data;


@Data
public class CommentDto implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private long id;
	
	private String commentId;
	private String commentText;
	private Date commentDate;
	
	private UserDto user;
	
	//private Product product;

}
