package com.example.techtask.model.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class GoodsDto implements Serializable{


	private Integer pk;
	private Integer amount;
	private String name;
	private Integer storeFK;

	public GoodsDto() {}
	
	public GoodsDto(int amount, String name, int storeFK){
		this.storeFK = storeFK;
		this.name = name;
		this.amount = amount;
	}
}
