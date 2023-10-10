package com.site3.paymentmanager.dao;

//import com.site3.paymentmanager.entities.Order;
import com.site3.paymentmanager.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
@RepositoryRestResource
public interface PayementRepository extends JpaRepository<Payment, Long> {

}
