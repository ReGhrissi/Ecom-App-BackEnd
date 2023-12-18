package com.site3.ecommerce.responses;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import lombok.Data;

@Data
public class ProductResponse {
	
	private long id;
	private String productId;
	private String photoName;
	private String name;
	private String description;
	private String details;
	
	private double price;
	private double currentPrice;
	
	private double stock;
	private double promotionRate;
	
	private boolean  newProduct;
	private boolean futurProduct;
	private boolean tendancyProduct;
	
	private boolean  promotionProduct;
	private boolean selectedProduct;
	private boolean availableProduct;

	//@Transient //ne sera pas ajouter dans la base de données
	private int quantity;
	
	private Date creationDate;
	
	private String categoryId;
	
	private List<CommentResponse> comments;

}
