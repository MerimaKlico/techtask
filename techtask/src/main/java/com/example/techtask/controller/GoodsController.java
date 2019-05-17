package com.example.techtask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.techtask.model.GoodsEntity;
import com.example.techtask.model.dto.GoodsDto;
import com.example.techtask.service.GoodsService;

@RestController
public class GoodsController {

	@Autowired
	private GoodsService goodsService;
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/addGoods")
	public Boolean addGoods(@RequestBody final GoodsDto goods) {
		return goodsService.addGoods(goods);
	}
}
