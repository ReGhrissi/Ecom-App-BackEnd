package com.site3.ecommerce.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.site3.ecommerce.entities.Category;


@CrossOrigin("*")
@RepositoryRestResource //spring Data Rest -> API Rest
public interface CategoryRepository extends JpaRepository<Category, Long>{
	
    @Query("select c from Category c where c.name like :kw")
    List<Category> searchCategory(@Param("kw") String keyword);

}
