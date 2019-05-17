package com.example.techtask.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.example.techtask.model.CalculatedroutesEntity;
import com.example.techtask.model.DepotEntity;
import com.example.techtask.model.GoodsEntity;

@Repository
public class GoodsRepository extends GenericDAO<GoodsEntity, Integer> {

	
	public List<GoodsEntity> findByName(String name){
		
		CriteriaQuery<GoodsEntity> criteriaQuery = criteriaBuilder.createQuery(GoodsEntity.class);
        Root<GoodsEntity> goodsEntity = criteriaQuery.from(GoodsEntity.class);
       
        criteriaQuery.where(criteriaBuilder.equal(goodsEntity.get("name"), name));
        return entityManager.createQuery(criteriaQuery).getResultList();
          
	}
	
	public void updateAmount (String name, Integer storeFK, Integer amount){
		CriteriaUpdate<GoodsEntity> criteriaQuery = criteriaBuilder.createCriteriaUpdate(GoodsEntity.class);
        Root<GoodsEntity> goodsEntity = criteriaQuery.from(GoodsEntity.class);

        List<Predicate> predicates = new ArrayList<Predicate>();

        predicates.add(criteriaBuilder.equal(goodsEntity.get("storeEntity").get("pk")
        		, storeFK));

        predicates.add(criteriaBuilder.equal(goodsEntity.get("name"), name));
        criteriaQuery.set(goodsEntity.get("amount"), amount);
        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));

        entityManager.createQuery(criteriaQuery).executeUpdate();
	}
}
