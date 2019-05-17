package com.example.techtask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.techtask.model.CustomerEntity;
import com.example.techtask.model.GoodsEntity;
import com.example.techtask.model.StoreEntity;
import com.example.techtask.model.dto.CustomerDto;
import com.example.techtask.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired 
	private CustomerRepository cr;
	
	@Autowired
	private CalculatedroutesService calculatedroutesService;
	
	public Boolean addCustomer(CustomerDto c) {
		
		CustomerEntity customerEntity = dtoToEntity(c);
		//CalculatedroutesEntity ce = sr.findByPK(goods.getStoreFK());
		//goodsEntity.setStore(s);
		try{
		cr.save(customerEntity);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		
		return true;
	}

	private CustomerEntity dtoToEntity(CustomerDto c) {
		
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setName(c.getName());
		customerEntity.setLatitude(c.getLatitude());
		customerEntity.setLongitude(c.getLongitude());

		return customerEntity;
	}

	
}
