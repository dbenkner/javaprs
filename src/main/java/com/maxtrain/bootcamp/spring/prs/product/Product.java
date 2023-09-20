package com.maxtrain.bootcamp.spring.prs.product;

import com.maxtrain.bootcamp.spring.prs.vendor.Vendor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="products", uniqueConstraints=@UniqueConstraint(name="UDIX_code", columnNames= {"partNbr"}))
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(length=30, nullable=false)
	private String partNbr = "";
	@Column(length=30, nullable=false)
	private String name = "";
	@Column(columnDefinition="decimal(11,2) not null")
	private double price;
	@Column(length=30, nullable=false)
	private String unit = "Qty";
	@Column(length=255, nullable=true)
	private String photoPath;
	@ManyToOne
	@JoinColumn(name="vendorId")
	private Vendor vendor;
	
	Product(){	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPartNbr() {
		return partNbr;
	}

	public void setPartNbr(String partNbr) {
		this.partNbr = partNbr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
	
}
