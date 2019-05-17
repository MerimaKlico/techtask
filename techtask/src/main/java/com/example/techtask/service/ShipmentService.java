package com.example.techtask.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.techtask.model.CalculatedroutesEntity;
import com.example.techtask.model.DepotEntity;
import com.example.techtask.model.GoodsEntity;
import com.example.techtask.model.StoreEntity;
import com.example.techtask.model.dto.CustomerDto;
import com.example.techtask.repository.DepotRepository;
import com.example.techtask.repository.GoodsRepository;
import com.example.techtask.repository.StoreRepository;

@Service
public class ShipmentService {

	@Autowired
	private CalculatedroutesService calculatedroutesService;
	
	@Autowired 
	private DepotRepository depotRepository;
	
	@Autowired
	private StoreRepository storeRepository;
	
	@Autowired
	private GoodsRepository goodsRepository;
	
	private static HashMap<String, List<Integer>> map1 = new HashMap<>();
	private static HashMap<Integer, LocalTime> map2 = new HashMap<>();

	
	private static List<StoreEntity> stores= new ArrayList<>();
	private static List<DepotEntity> depots= new ArrayList<>();

	
public List<CalculatedroutesEntity> makeShipment (String goodsName, CustomerDto customer) {
	
    LocalTime time = LocalTime.now();  
		
	List<Integer> availableStoresPK = new ArrayList<>();
	if (stores.isEmpty()){
		stores = storeRepository.findAll();
	}
	if (depots.isEmpty()){
		depots = depotRepository.findAll();
	}
	List<GoodsEntity> goodsEntities = goodsRepository.findByName(goodsName);
	
		if (map1.containsKey(goodsName)){
			availableStoresPK = map1.get(goodsName);
		}else{
			for (GoodsEntity g : goodsEntities){
				availableStoresPK.add(g.getStore().getPk());
				}
			map1.put(goodsName,availableStoresPK);
		}
	
		
		CalculatedroutesEntity chosenCalculatedroute = new CalculatedroutesEntity();
		
		List<CalculatedroutesEntity> calculatedroutes = 		
				calculatedroutesService.getMinDistances(customer.getPk(), availableStoresPK);
				
		int timeIntervalDepotCustomer =0;
		
		for(int i=0; i<calculatedroutes.size(); i++) {
			
			chosenCalculatedroute = calculatedroutes.get(i);
			int timeIntervalDepotCustomer1 = (int) ((chosenCalculatedroute.getRouteDistance() / 60) * 1000/3600);
			if(chosenCalculatedroute.getDepot().getAvailableDrones() ==0){
				if (i+1 < calculatedroutes.size()){
				LocalTime x = map2.get(chosenCalculatedroute.getDepot().getPk());
				int timeIntervalDepotCustomer2 = (int) ((calculatedroutes.get(i+1).getRouteDistance() / 60) * 1000/3600);
				//LocalTime z = calculatedroutes.get(i+1).getDepot().get
				}
			}
		}

			

		
		for (CalculatedroutesEntity c : calculatedroutes){
			time = time.plusSeconds(timeIntervalDepotCustomer);

			if (c.getDepot().getAvailableDrones() == 0){
				LocalTime x = map2.get(c.getDepot().getPk());
				if (map2.get(c.getDepot().getPk()).isBefore(time)){
					chosenCalculatedroute = c;
					break;
				}
			}else {
				chosenCalculatedroute = c;
				break;
			}
		}
		
		for (DepotEntity d : depots){
			if (d.getPk().equals(chosenCalculatedroute.getDepot().getPk())){
				int value = d.getAvailableDrones();
				d.setAvailableDrones(--value);
			}
		}
		
		for (GoodsEntity g : goodsEntities){
			Integer storePK = g.getStore().getPk();
			if (storePK.equals(chosenCalculatedroute.getStore().getPk())){
				Integer amount = g.getAmount() -1;
				goodsRepository.updateAmount(goodsName, storePK, amount);
				if (amount.equals(0)){
					if (map1.containsKey(goodsName)){
						map1.get(goodsName).remove(storePK);
					}
				}
			}
		}
		
		int timeIntervalCustomerDepot =0;
		
		time = time.plusSeconds(timeIntervalDepotCustomer+timeIntervalCustomerDepot);
		if (map2.containsKey(chosenCalculatedroute.getDepot().getPk())){
			if (map2.get(chosenCalculatedroute.getDepot().getPk()).isAfter(time)){
				map2.replace(chosenCalculatedroute.getDepot().getPk(), time);
			}
		}else{
			map2.put(chosenCalculatedroute.getDepot().getPk(), time);
		}
			
		return calculatedroutes;
	}


public CalculatedroutesEntity makeShipment(Integer goods, CustomerDto customer) {
	// TODO Auto-generated method stub
	return null;
}

}
