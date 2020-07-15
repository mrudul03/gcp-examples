package com.service.customer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.service.customer.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String>{

}
