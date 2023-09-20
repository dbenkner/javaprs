package com.maxtrain.bootcamp.spring.prs.requestlines;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.maxtrain.bootcamp.spring.prs.product.Product;
import com.maxtrain.bootcamp.spring.prs.request.Request;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="requestlines")
public class Requestline {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="requestId")
	public Request request;
	@ManyToOne
	@JoinColumn(name="productId")
	@JsonIgnore
	public Product product;
	private int quantity = 1;
	
	Requestline(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Request getReqeust() {
		return request;
	}

	public void setReqeust(Request reqeust) {
		this.request = reqeust;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
