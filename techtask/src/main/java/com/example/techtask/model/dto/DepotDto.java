package com.example.techtask.model.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class DepotDto implements Serializable{

	private Integer pk;
	private String name;	
	private Double longitude;	
	private Double latitude;
	
	public DepotDto() {}
	
	public DepotDto(String name, Double latitude, Double longitude) {
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
	}
}
