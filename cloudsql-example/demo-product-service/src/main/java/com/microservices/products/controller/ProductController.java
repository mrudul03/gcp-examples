package com.microservices.products.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.products.contract.ProductResource;
import com.microservices.products.repository.Product;
import com.microservices.products.repository.ProductsRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api/products-service")
@Api(value = "Products Service")
public class ProductController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	@Autowired
	private ProductsRepository productsRepository;
	
	@ApiOperation(value = "Get all products")
	@GetMapping("/products")
	public ResponseEntity<List<ProductResource>> getAllProducts(){
		
		LOGGER.info("in getAllProducts.........");
		List<ProductResource> products = new ArrayList<ProductResource>();
		List<Product> productEntities = productsRepository.findAll();
		LOGGER.info("-:"+productEntities.size());
		
		for(Product entity:productEntities) {
			products.add(new ProductResource(entity.getId(), entity.getTitle(), entity.getDescription()));
		}
		return ResponseEntity.ok(products);
	}
}
