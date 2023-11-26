package com.site3.ecommerce.requests;

import javax.validation.constraints.NotNull;

import lombok.Data;



@Data
public class ProductRequest {
	
	private long id;
	
	private String productId;
	
	@NotNull(message=" Le champ name ne doit pas etre null !")
	private String name;
	
	@NotNull(message="Le champ description ne doit pas etre null !")
	private String description;
	
	@NotNull(message="Le champ price ne doit pas etre null !")
	private double price;
	
	@NotNull(message="Le champ currentPrice ne doit pas etre null !")
	private double currentPrice;
	
	private int stock;
	private double promotionRate;
	
	private boolean  newProduct;
	private boolean  futurProduct;
	private boolean  tendancyProduct;
	private boolean  promotionProduct;
	private boolean selectedProduct;
	private boolean availableProduct;

	//@Transient //ne sera pas ajouter dans la base de données
	private int quantity=1;


}
