package com.site3.ecommerce.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;


@Data
public class CategoryDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String categoryId;
	private String photoName = "unknown.jpg";
	
	private String name;
	private String description;
	
	private boolean active = true;
	
	private List<ProductDto> products;
	
	
}
