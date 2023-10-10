package com.site3.clientsorders.dao;

import com.site3.clientsorders.entities.Client;
import com.site3.clientsorders.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
@RepositoryRestResource
public interface OrderRepository extends JpaRepository<Order, Long> {

}
