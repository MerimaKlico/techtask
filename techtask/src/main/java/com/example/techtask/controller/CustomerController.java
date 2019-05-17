package com.example.techtask.controller;

import com.example.techtask.model.CustomerEntity;
import com.example.techtask.model.dto.CustomerDto;
import com.example.techtask.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService cs;
	
	@RequestMapping(method = RequestMethod.POST, value = "/addCustomer")
	public Boolean addCustomer (@RequestBody final CustomerDto c) {
		
		return cs.addCustomer(c);
	}
	
}
