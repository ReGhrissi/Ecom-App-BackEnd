package com.site3.ecommerce.dto;

import java.io.Serializable;

import lombok.Data;



@Data 
public class CategoryDTO implements Serializable{
	

	private Long id;
	private String name;
	private String description;
	
}
