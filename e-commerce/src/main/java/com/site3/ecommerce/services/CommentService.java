package com.site3.ecommerce.services;

import java.util.List;
import com.site3.ecommerce.dto.CommentDto;


public interface CommentService {
	
	List<CommentDto> getAllComments(String email);
	
	CommentDto createComment(CommentDto comment, String email);
	
    CommentDto getComment(String commentId);
	
	void deleteComment(String commentId);

}
