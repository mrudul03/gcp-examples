package com.service.customer.model;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address implements GeneratedId {
    @Id
    private String id;
    private String city;
    private String state;
    private String street;
    private String zipcode;
}