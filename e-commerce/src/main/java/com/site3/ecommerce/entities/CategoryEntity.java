package com.site3.ecommerce.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @NoArgsConstructor @AllArgsConstructor @ToString
@Entity
@Table(name="categories")
public class CategoryEntity implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	//@Column(nullable=false)
	private String categoryId;
	
	private String photoName = "unknown.jpg";
	
	@Column(nullable=false, length=120, unique=true)
	private String name;
	
	//@Column(nullable=false)
	private String description;
	
	private boolean active =true;
	
	@OneToMany(mappedBy = "category", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private List<ProductEntity> products;
	

}
