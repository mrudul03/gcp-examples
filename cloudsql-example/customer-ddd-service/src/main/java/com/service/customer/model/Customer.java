package com.service.customer.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements GeneratedId{
	
	@Id
    private String id;
	private String firstname;
	private String lastname;
	private String mobilenumber;
	
	@MappedCollection(idColumn = "id")
    private Address address;

}
