package com.example.techtask.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.springframework.stereotype.Repository;

import com.example.techtask.model.CalculatedroutesEntity;
import com.example.techtask.model.DepotEntity;

@Repository
public class DepotRepository extends GenericDAO<DepotEntity, Integer>{

	
	public List<Integer> findAllPK(){
		
		CriteriaQuery<Integer> criteriaQuery = criteriaBuilder.createQuery(Integer.class);
        Root<DepotEntity> root = criteriaQuery.from(DepotEntity.class);

        criteriaQuery.select(root.get("pk"));
        return entityManager.createQuery(criteriaQuery).getResultList();
	}
	
	public void updateAvailableDepots(Integer depotPK, Integer availableDrones){
		CriteriaUpdate<DepotEntity> criteriaQuery = criteriaBuilder.createCriteriaUpdate(DepotEntity.class);
        Root<DepotEntity> depotEntity = criteriaQuery.from(DepotEntity.class);

        Predicate predicate = criteriaBuilder.equal(depotEntity.get("pk"), depotPK);

        criteriaQuery.set(depotEntity.get("availableDrones"), availableDrones);
        criteriaQuery.where(predicate);
        entityManager.createQuery(criteriaQuery).executeUpdate();
	}
}
