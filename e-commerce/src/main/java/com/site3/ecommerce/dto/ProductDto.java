package com.site3.ecommerce.dto;

import java.io.Serializable;

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
	
	private double price;
	private double currentPrice;
	private double promotionRate;
	
	private boolean  newProduct;
	private boolean  futurProduct;
	private boolean  tendancyProduct;
	private boolean  promotionProduct;
	private boolean selectedProduct;
	private boolean availableProduct;
	
	@Transient //ne sera pas ajouter dans la base de données
	private int quantity=1;
	private int stock;
	
	private CategoryDto category;
	

}
