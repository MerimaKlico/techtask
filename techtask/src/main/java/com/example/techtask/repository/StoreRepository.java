package com.example.techtask.repository;

import org.springframework.stereotype.Repository;

import com.example.techtask.model.CustomerEntity;
import com.example.techtask.model.StoreEntity;

@Repository
public class StoreRepository extends GenericDAO<StoreEntity, Integer> {

}
