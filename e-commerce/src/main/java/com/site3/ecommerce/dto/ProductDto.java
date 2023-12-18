package com.site3.ecommerce.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Transient;

import lombok.Data;




@Data
public class ProductDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private long id;
	

	private String productId;
	private String photoName = "unknown.jpg";
	
	private String name;
	private String description;
	private String details;
	
	private double price;
	private double currentPrice;
	private double promotionRate;
	
	private Boolean  newProduct;
	private Boolean  futurProduct;
	private Boolean  tendancyProduct;
	private Boolean  promotionProduct;
	private Boolean selectedProduct;
	private Boolean availableProduct;
	
	@Transient //ne sera pas ajouter dans la base de données
	private int quantity=1;
	private int stock;
	
	private Date creationDate;
	
	private CategoryDto category;
	
	private List<CommentDto> comments;
	

}
