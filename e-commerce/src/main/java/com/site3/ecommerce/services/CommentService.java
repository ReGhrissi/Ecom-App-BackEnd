package com.site3.ecommerce.services;

import java.util.List;
import com.site3.ecommerce.dto.CommentDto;
import com.site3.ecommerce.dto.ProductDto;
import com.site3.ecommerce.entities.ProductEntity;


public interface CommentService {
	
	List<CommentDto> getAllComments(String email);
	
	CommentDto createComment(CommentDto comment, ProductEntity productEntity, String email);
	
    CommentDto getComment(String commentId);
	
	void deleteComment(String commentId);

}
