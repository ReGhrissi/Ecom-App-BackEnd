package com.site3.ecommerce.responses;

import java.util.List;


import lombok.Data;

@Data
public class ProductResponse {
	
	private String productId;
	private String photoName;
	private String name;
	private String description;
	
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
	
	private String categoryId;
	
	private List<CommentResponse> comments;

}
