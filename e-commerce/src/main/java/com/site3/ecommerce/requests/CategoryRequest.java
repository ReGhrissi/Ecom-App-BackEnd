package com.site3.ecommerce.requests;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.site3.ecommerce.responses.ProductResponse;

import lombok.Data;

@Data
public class CategoryRequest {
	
	@NotNull(message="Ce champ ne doit pas etre null !")
	private String name;
    
	@NotNull(message="Ce champ ne doit pas etre null !")
	private String description;
	
	private boolean active;
	
	private List<ProductRequest> products;

}
