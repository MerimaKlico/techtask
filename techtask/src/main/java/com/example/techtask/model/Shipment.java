package com.example.techtask.model;

import lombok.Data;

@Data
public class Shipment {
	
	private GoodsEntity goods;
	private CustomerEntity customer;
	
	public Shipment() {}
	public Shipment(GoodsEntity goods, CustomerEntity customer) {
		this.goods = goods;
		this.customer = customer;
	}

}
