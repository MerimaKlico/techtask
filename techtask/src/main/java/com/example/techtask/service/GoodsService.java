package com.example.techtask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.techtask.model.GoodsEntity;
import com.example.techtask.model.StoreEntity;
import com.example.techtask.model.dto.GoodsDto;
import com.example.techtask.repository.GoodsRepository;
import com.example.techtask.repository.StoreRepository;

@Service
public class GoodsService {

	@Autowired 
	private GoodsRepository gr;
	
	@Autowired 
	private StoreRepository sr;

	
	public Boolean addGoods(GoodsDto goods) {
		
		GoodsEntity goodsEntity = new GoodsEntity();
		goodsEntity =  dtoToEntity(goods, goodsEntity);
		StoreEntity s = sr.findByPK(goods.getStoreFK());
		goodsEntity.setStore(s);
		//goodsEntity.setStore(new StoreEntity(s.getPk()));
		try{
		gr.save(goodsEntity);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		return true;
	}

	public GoodsEntity dtoToEntity(GoodsDto goods, GoodsEntity goodsEntity) {
				
		goodsEntity.setName(goods.getName());
		goodsEntity.setAmount(goods.getAmount());
		
		return goodsEntity;
		
	}

}
