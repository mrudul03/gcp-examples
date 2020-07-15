package com.service.customer.contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerContract {
	
	private String id;
	private String firstname;
	private String lastname;
	private String mobilenumber;
	private AddressContract address;
}
