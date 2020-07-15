package com.service.customer.contract;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressContract {
	
	private String id;
    private String city;
    private String state;
    private String street;
    private String zipcode;

}
