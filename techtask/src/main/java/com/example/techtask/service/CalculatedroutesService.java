package com.example.techtask.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.techtask.repository.*;
import com.example.techtask.model.*;

@Service
public class CalculatedroutesService {

	
	@Autowired 
	private CalculatedroutesRepository crr;
	
	@Autowired
	private StoreRepository sr;
	
	@Autowired
	private DepotRepository dr;
	
	@Autowired
	private CustomerRepository cr;
	
	public void calculateDistancesWhenAddingNewCustomer(CustomerEntity customer) {
		
		List<StoreEntity> storeEntities = sr.findAll();
		List<DepotEntity> depotEntities = dr.findAll();
		
		List<CalculatedroutesEntity> calculatedroutesEntities = new ArrayList<>();
		
		for (int i = 0; i < storeEntities.size(); i++){
			
			StoreEntity storeEntity = storeEntities.get(i);
			
			double distanceStoreCustomer = calculateDistance(storeEntity.getLatitude(), 
					storeEntity.getLongitude(), customer.getLatitude(), customer.getLongitude());
			
			
			for (int j = 0; j < depotEntities.size(); j++){
				
				DepotEntity depotEntity = depotEntities.get(j);
				
				double distanceDepotStore = calculateDistance(storeEntity.getLatitude(), 
						storeEntity.getLongitude(), depotEntity.getLatitude(), depotEntity.getLongitude());
				
				double routeDistance = distanceStoreCustomer + distanceDepotStore;
				CalculatedroutesEntity calculatedroutesEntity = new CalculatedroutesEntity();
				calculatedroutesEntity.setCustomer(customer);
				calculatedroutesEntity.setDepot(depotEntity);
				calculatedroutesEntity.setStore(storeEntity);
				calculatedroutesEntity.setRouteDistance(routeDistance);
				crr.save(calculatedroutesEntity);
			}
		}
		
	}
	
	


public void calculateDistancesWhenAddingNewStore(StoreEntity store) {
	
	List<CustomerEntity> customerEntities = cr.findAll();
	List<DepotEntity> depotEntities = dr.findAll();
	
	List<CalculatedroutesEntity> calculatedroutesEntities = new ArrayList<>();
	
	for (int i = 0; i < customerEntities.size(); i++){
		
		CustomerEntity customerEntity = customerEntities.get(i);
		
		double distanceStoreCustomer = calculateDistance(store.getLatitude(), 
				store.getLongitude(), customerEntity.getLatitude(), customerEntity.getLongitude());
		
		
		for (int j = 0; j < depotEntities.size(); j++){
			
			DepotEntity depotEntity = depotEntities.get(j);
			
			double distanceDepotStore = calculateDistance(store.getLatitude(), 
					store.getLongitude(), depotEntity.getLatitude(), depotEntity.getLongitude());
			
			double routeDistance = distanceStoreCustomer + distanceDepotStore;
			CalculatedroutesEntity calculatedroutesEntity = new CalculatedroutesEntity();
			calculatedroutesEntity.setCustomer(customerEntity);
			calculatedroutesEntity.setDepot(depotEntity);
			calculatedroutesEntity.setStore(store);
			calculatedroutesEntity.setRouteDistance(routeDistance);
			crr.save(calculatedroutesEntity);
		}
	}
	
}

	private double calculateDistance(Double lat1, Double long1, Double lat2,
			Double long2) {

		final int R = 6371; // Radius of the earth

		double latDistance = Math.toRadians(lat2 - lat1);
	    double longDistance = Math.toRadians(long2 - long1);
	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
	            * Math.sin(longDistance / 2) * Math.sin(longDistance / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	  
	    double distance = R * c;
	    return Math.sqrt(distance);
		
	}
	
	public List<CalculatedroutesEntity> getMinDistances (Integer customerPK, List<Integer> stores){
		
		List<CalculatedroutesEntity> minDistances = crr.getMinDistances(customerPK, stores);
		return minDistances;
		}
	

}
