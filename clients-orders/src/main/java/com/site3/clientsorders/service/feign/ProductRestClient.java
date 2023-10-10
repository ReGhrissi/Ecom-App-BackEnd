package com.site3.clientsorders.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.site3.clientsorders.service.model.Product;

import lombok.Data;

@FeignClient(name ="E-COMMERCE")
public interface ProductRestClient {
	
	@GetMapping(path="/products/{id}")
	Product getProductById(@PathVariable(name="id") Long id);
	
	@GetMapping(path="/products/")
	PagedModel<Product> getPagetProducts(@RequestParam(value="page") int page,
								   @RequestParam(name="size") int size);


}
