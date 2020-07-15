package com.service.customer.transform;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.service.customer.contract.AddressContract;
import com.service.customer.contract.CustomerContract;
import com.service.customer.model.Address;
import com.service.customer.model.Customer;

@Component
public class CustomerTransformer {
	
	private Function<Customer, CustomerContract> transformCustomerToContract = new 
			Function<Customer, CustomerContract>(){

				@Override
				public CustomerContract apply(Customer customer) {
					CustomerContract customerContract = new CustomerContract();
					customerContract.setId(customer.getId());
					customerContract.setFirstname(customer.getFirstname());
					customerContract.setLastname(customer.getLastname());
					customerContract.setMobilenumber(customer.getMobilenumber());
					
					AddressContract addressContract = new AddressContract();
					if(null != customer.getAddress()) {
						addressContract.setId(customer.getAddress().getId());
						addressContract.setCity(customer.getAddress().getCity());
						addressContract.setState(customer.getAddress().getState());
						addressContract.setStreet(customer.getAddress().getStreet());
						addressContract.setZipcode(customer.getAddress().getZipcode());
					}
					customerContract.setAddress(addressContract);
					return customerContract;
				}
		
	};
	
	private Function<CustomerContract, Customer> transformCustomerContractToCustomer = new 
			Function<CustomerContract, Customer>(){

				@Override
				public Customer apply(CustomerContract customerContract) {
					Customer customer = new Customer();
					customer.setFirstname(customerContract.getFirstname());
					customer.setLastname(customerContract.getLastname());
					customer.setMobilenumber(customerContract.getMobilenumber());
					
					Address address = new Address();
					if(null != customerContract.getAddress()) {
						address.setCity(customerContract.getAddress().getCity());
						address.setState(customerContract.getAddress().getState());
						address.setStreet(customerContract.getAddress().getStreet());
						address.setZipcode(customerContract.getAddress().getZipcode());
					}
					customer.setAddress(address);
					return customer;
				}
		
	};
	
	public CustomerContract transformCustomer(Customer customer) {
		return this.transformCustomerToContract.apply(customer);
	}
	
	public List<CustomerContract> transformCustomerList(Collection<Customer> customerEntities){
		
		List<CustomerContract> customerResources = customerEntities.stream().
				map(entity -> transformCustomer(entity)).
				collect(Collectors.toList());
		return customerResources;
	}
	
	public Customer transformCustomerContract(CustomerContract customerContract) {
		return this.transformCustomerContractToCustomer.apply(customerContract);
	}

}
