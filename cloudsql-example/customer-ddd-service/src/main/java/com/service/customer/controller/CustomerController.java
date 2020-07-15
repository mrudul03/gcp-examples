package com.service.customer.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.customer.contract.CustomerContract;
import com.service.customer.model.Customer;
import com.service.customer.repository.CustomerRepository;
import com.service.customer.transform.CustomerTransformer;

@RestController
@RequestMapping(value="/api/customer-service")
public class CustomerController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CustomerTransformer customerTransformer;
	
	@GetMapping("/customers")
	public ResponseEntity<List<CustomerContract>> getAllCustomers(){
		
		LOGGER.info("in getAllCustomers .........");
		//customerRepository.findAll().iterator().forEachRemaining(customers::add);
		
		Iterable<Customer> custIterable = customerRepository.findAll();
		Collection<Customer> custCollection = new ArrayList<>();
		custIterable.forEach(custCollection::add);
		
		List<CustomerContract> customers = customerTransformer.transformCustomerList(custCollection);
		
		return ResponseEntity.ok(customers);
	}
	
	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE,
			 value="/customers")
	public ResponseEntity<Customer> createCustomers(@RequestBody 
			CustomerContract customerContract){
		
		Customer customer = customerTransformer.transformCustomerContract(customerContract);
		Customer savedCustomer = customerRepository.save(customer);
		
		return ResponseEntity.ok(savedCustomer);
		
	}

}
