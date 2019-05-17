package com.example.techtask.model.dto;

import java.io.Serializable;

import lombok.Data;
@Data
public class StoreDto implements Serializable{

	private Integer pk;
	private String name;	
	private String address;
	private Double longitude;	
	private Double latitude;
	
	public StoreDto() {}
	
	public StoreDto(String name, String address, Double latitude, Double longitude) {
		this.name = name;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
	}
}
