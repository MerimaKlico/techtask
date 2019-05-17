package com.example.techtask.model.dto;

import com.example.techtask.model.CustomerEntity;
import com.example.techtask.model.DepotEntity;
import com.example.techtask.model.StoreEntity;

import lombok.Data;

@Data
public class CalculatedroutesDto implements java.io.Serializable {

	private int pk;
	private int customerFK;
	private int depotFK;
	private int storeFK;
	private Double routeDistance;

	public CalculatedroutesDto() {
	}

	public CalculatedroutesDto(int customerFK, int depotFK, int storeFK, double routeDistance) {
		
		this.customerFK = customerFK;
		this.storeFK = storeFK;
		this.depotFK = depotFK;
		this.routeDistance = routeDistance;
	}
	
}
