package com.site3.ecommerce.requests;

import java.util.Date;

import lombok.Data;

@Data
public class CommentRequest {

	private String commentText;
	private Date commentDate;
	
	//private Long productId;
}
