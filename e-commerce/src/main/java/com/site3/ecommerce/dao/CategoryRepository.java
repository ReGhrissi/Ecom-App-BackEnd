package com.site3.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.site3.ecommerce.entities.Category;


@CrossOrigin("*")
@RepositoryRestResource //spring Data Rest -> API Rest
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
