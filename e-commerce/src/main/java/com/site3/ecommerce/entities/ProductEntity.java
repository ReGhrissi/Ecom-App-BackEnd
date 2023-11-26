package com.site3.ecommerce.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @NoArgsConstructor @AllArgsConstructor @ToString
@Entity
@Table(name="products")
public class ProductEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable=false)
	private String productId;
	
	private String photoName = "unknown.jpg";
	
	@Column(nullable=false, length=120)
	private String name;
	
	@Column(nullable=false)
	private String description;
	
	@Column(nullable=false)
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
	
	@ManyToOne
	@JoinColumn(name="categories_id")
	private CategoryEntity category;
	
	@OneToMany(mappedBy="product", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private List<CommentEntity> comments;
	
	@OneToOne(mappedBy="product", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private OrderItemEntity orderItem;
}
