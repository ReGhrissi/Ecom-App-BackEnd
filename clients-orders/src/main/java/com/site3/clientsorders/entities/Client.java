package com.site3.clientsorders.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;


@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString

public class Client {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
    private String username;
    
	//@OneToMany(mappedBy = "client")
	//@Transient
	//private Order orders;
	//private Collection<Order> orders;
}
