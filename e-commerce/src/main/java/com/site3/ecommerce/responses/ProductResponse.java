package com.site3.ecommerce.responses;

import javax.persistence.Transient;

import lombok.Data;

@Data
public class ProductResponse {
	
	private String productId;
	private String photoName;
	private String name;
	private String description;
	private double currentPrice;
	private boolean  promotion;
	private boolean selected;
	private boolean available;

	//@Transient //ne sera pas ajouter dans la base de données
	private int quantity;

}
