package com.example.techtask.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.techtask.model.CalculatedroutesEntity;
import com.example.techtask.model.DepotEntity;
import com.example.techtask.model.StoreEntity;
import com.example.techtask.model.dto.CustomerDto;
import com.example.techtask.service.CalculatedroutesService;
import com.example.techtask.service.CustomerService;
import com.example.techtask.service.ShipmentService;

public class ShipmentController {
	
	@Autowired
	private ShipmentService shipmentService;
		
	@RequestMapping(method = RequestMethod.GET, value = "/makeShipment")
	
	public CalculatedroutesEntity makeShipment (@RequestParam final Integer goods, @RequestBody CustomerDto customer) {
		
		return shipmentService.makeShipment(goods, customer);
	}

}
