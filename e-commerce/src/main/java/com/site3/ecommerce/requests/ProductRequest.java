package com.site3.ecommerce.requests;

import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ProductRequest {
	
	@NotNull(message=" Le champ name ne doit pas etre null !")
	private String name;
	
	@NotNull(message="Le champ description ne doit pas etre null !")
	private String description;
	
	@NotNull(message="Le champ currentPrice ne doit pas etre null !")
	private double currentPrice;
	
	private boolean  promotion;
	private boolean selected;
	private boolean available;

	//@Transient //ne sera pas ajouter dans la base de données
	private int quantity=1;


}
