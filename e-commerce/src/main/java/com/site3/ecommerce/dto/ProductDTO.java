package com.site3.ecommerce.dto;

import java.io.Serializable;

import lombok.Data;



@Data 
public class ProductDTO implements Serializable {
	
	
	private Long id;
	private String name;
	private String description;
	private double currentPrice;
	private boolean  promotion;
	private boolean selected;
	private boolean available;
	private String photoName;
	private int quantity=1;
	
	private CategoryDTO categoryDTO;
	

}
