package com.site3.ecommerce.web;

import java.lang.reflect.Type;
import java.security.Principal;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.site3.ecommerce.dto.CommentDto;
import com.site3.ecommerce.requests.CommentRequest;
import com.site3.ecommerce.responses.CommentResponse;
import com.site3.ecommerce.services.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {
	

	@Autowired
	CommentService commentService;
	
	@GetMapping
	public ResponseEntity<List<CommentResponse>>getComments(Principal principal) {
		
		List<CommentDto> comments = commentService.getAllComments(principal.getName());
		
		Type listType = new TypeToken<List<CommentResponse>>() {}.getType();
		
		List<CommentResponse> commentsResponse = new ModelMapper().map(comments, listType);
		
		return new ResponseEntity<List<CommentResponse>>(commentsResponse, HttpStatus.OK);
		
	}
	
	
	@PostMapping(
			consumes={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE}, 
		    produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
			)
	public ResponseEntity<CommentResponse> StoreComment(@RequestBody CommentRequest commentRequest, Principal principal) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		CommentDto commentDto = modelMapper.map(commentRequest, CommentDto.class);
		CommentDto createComment = commentService.createComment(commentDto, principal.getName());
		
		CommentResponse newComment = modelMapper.map(createComment, CommentResponse.class);
		
		return new ResponseEntity<CommentResponse>(newComment, HttpStatus.CREATED);
	}

	
	@GetMapping("/{id}")
	public  ResponseEntity<CommentResponse> getOneComment(@PathVariable(name="id") String commentId) {
		
		CommentDto commentDto = commentService.getComment(commentId);
		
		ModelMapper modelMapper = new ModelMapper();
		
		CommentResponse commentResponse = modelMapper.map(commentDto, CommentResponse.class);
		
		return new ResponseEntity<CommentResponse>(commentResponse, HttpStatus.OK);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateComment(@PathVariable(name="id") String commentId) {
		return new ResponseEntity<>("update comment", HttpStatus.ACCEPTED);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteComment(@PathVariable(name="id") String commentId) {
		
		commentService.deleteComment(commentId);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
}
