package com.site3.ecommerce.dao;


import com.site3.ecommerce.entities.Payment;
import com.site3.ecommerce.entities.PaymentEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

//@CrossOrigin("*")
//@RepositoryRestResource
@Repository
public interface PayementRepository extends PagingAndSortingRepository<PaymentEntity, Long> {

}
