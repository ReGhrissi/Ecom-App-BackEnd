package com.site3.clientsorders.service.model;

import lombok.Data;

@Data
public class Product {
	
	private Long id;
	private String name;
	private String description;
	private double currentPrice;
	private boolean  promotion;
	private boolean selected;
	private boolean available;
	private String photoName;
	//@Transient //ne sera pas ajouter dans la base de donnees
	//private int quantity=1;
	
	//@ManyToOne
	//private Category category;


}

