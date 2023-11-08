package com.site3.ecommerce.responses;

import java.util.List;

import lombok.Data;

@Data
public class CategoryResponse {

	private String categoryId;
	private String photoName;
	private String name;
	private String description;
	private boolean active;
	
	private List<ProductResponse> products;
}
