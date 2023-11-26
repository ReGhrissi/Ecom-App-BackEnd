package com.site3.ecommerce.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.site3.ecommerce.enums.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data @NoArgsConstructor @AllArgsConstructor @ToString
@Entity
@Table(name="orders")
public class OrderEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable=false)
	private String orderId;
	
    private Date orderDate;
    private double totalAmount;
    
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private String reasonOfStatus;
    
    @OneToMany(mappedBy="order", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<OrderItemEntity> orderItems;
    
	@ManyToOne
	@JoinColumn(name="clients_id")
    private ClientEntity client;
	
	@OneToOne(mappedBy="order", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private PaymentEntity payment;
}
