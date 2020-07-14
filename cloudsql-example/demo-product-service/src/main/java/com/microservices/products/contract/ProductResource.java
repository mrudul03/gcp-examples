package com.microservices.products.contract;

import java.io.Serializable;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
public class ProductResource implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -362132034918196280L;
	private Integer id;
	private String title;
	private String description;
	
	public ProductResource() {}
	
	public ProductResource(Integer id, String title, String description) {
		this.id=id;
		this.title = title;
		this.description = description;
				
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
