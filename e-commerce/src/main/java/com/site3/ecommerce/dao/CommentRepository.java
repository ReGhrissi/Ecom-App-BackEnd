package com.site3.ecommerce.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.site3.ecommerce.entities.AddressEntity;
import com.site3.ecommerce.entities.CommentEntity;
import com.site3.ecommerce.entities.UserEntity;

@Repository
public interface CommentRepository extends CrudRepository<CommentEntity, Long> {
	
	List<CommentEntity> findByUser(UserEntity currentUser);
	
	CommentEntity findByCommentId(String commentId);

}
