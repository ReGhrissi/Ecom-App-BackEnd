package com.site3.ecommerce.services.impl;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.site3.ecommerce.dao.CommentRepository;
import com.site3.ecommerce.dao.UserRepository;
import com.site3.ecommerce.dto.CommentDto;
import com.site3.ecommerce.dto.UserDto;
import com.site3.ecommerce.entities.CommentEntity;
import com.site3.ecommerce.entities.UserEntity;
import com.site3.ecommerce.services.CommentService;
import com.site3.ecommerce.shared.Utils;


@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Utils util;
	

	@Override
	public List<CommentDto> getAllComments(String email) {
		
		UserEntity currentUser = userRepository.findByEmail(email);
		
		List<CommentEntity> comments = currentUser.getAdmin() == true ? (List<CommentEntity>) commentRepository.findAll() : commentRepository.findByUser(currentUser);
		
		Type listType = new TypeToken<List<CommentDto>>() {}.getType();
		
		List<CommentDto> commentsDto = new ModelMapper().map(comments, listType);
		
		return commentsDto;
	}

	
	
	@Override
	public CommentDto createComment(CommentDto comment, String email) {
		
		UserEntity currentUser = userRepository.findByEmail(email);
		
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(currentUser, UserDto.class);
		
		comment.setCommentId(util.generateStringId(30));
		comment.setUser(userDto);
		
		CommentEntity commentEntity = modelMapper.map(comment, CommentEntity.class); 
		
		CommentEntity newComment = commentRepository.save(commentEntity);
		
		CommentDto commentDto = modelMapper.map(newComment, CommentDto.class);
		
		return commentDto;
	}
 
	
	
	@Override
	public CommentDto getComment(String commentId) {
		
		CommentEntity commentEntity = commentRepository.findByCommentId(commentId);
		
		ModelMapper modelMapper = new ModelMapper();
		
		CommentDto commentDto = modelMapper.map(commentEntity, CommentDto.class);
		
		return commentDto;
	}

	
	
	@Override
	public void deleteComment(String commentId) {
		
		CommentEntity comment = commentRepository.findByCommentId(commentId);
		
		if(comment == null) throw new RuntimeException("Comment not found");
		
		commentRepository.delete(comment);
	}

}
