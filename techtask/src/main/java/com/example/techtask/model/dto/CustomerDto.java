package com.example.techtask.model.dto;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.example.techtask.model.CalculatedroutesEntity;

import lombok.Data;

@Data
public class CustomerDto implements Serializable{

	private Integer pk;
	private String name;	
	private Double longitude;	
	private Double latitude;
	
	public CustomerDto() {}
	
	public CustomerDto(String name, Double latitude, Double longitude) {
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
}
