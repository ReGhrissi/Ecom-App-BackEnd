package com.site3.ecommerce.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString

public class Product implements Serializable {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private double currentPrice;
	private boolean  promotion;
	private boolean selected;
	private boolean available;
	private String photoName;
	@Transient //ne sera pas ajouter dans la base de données
	private int quantity=1;
	
	@ManyToOne
	private Category category;
	
//	@OneToMany(mappedBy="product", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
//	private List<CommentEntity> comments;

}
