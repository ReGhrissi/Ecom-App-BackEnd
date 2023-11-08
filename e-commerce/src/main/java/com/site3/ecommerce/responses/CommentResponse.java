package com.site3.ecommerce.responses;

import java.util.Date;

import lombok.Data;

@Data
public class CommentResponse {

	private String commentId;
	private String commentText;
	private Date commentDate;
	
	private UserResponse userResponse;
	
}
