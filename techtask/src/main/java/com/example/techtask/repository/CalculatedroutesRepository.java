package com.example.techtask.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


import com.example.techtask.model.CalculatedroutesEntity;

@Repository
public class CalculatedroutesRepository extends GenericDAO<CalculatedroutesEntity, Integer> {

	public List<CalculatedroutesEntity> getMinDistances (Integer customerPK, List<Integer> stores){
		
		CriteriaQuery<CalculatedroutesEntity> criteriaQuery = criteriaBuilder.createQuery(CalculatedroutesEntity.class);
        Root<CalculatedroutesEntity> calculatedroutesEntity = criteriaQuery.from(CalculatedroutesEntity.class);

        List<Predicate> predicates = new ArrayList<Predicate>();

        predicates.add(criteriaBuilder.equal(calculatedroutesEntity.get("customerEntity").get("pk")
        		, customerPK));

        
        for(Integer s : stores){
            Predicate predicate = criteriaBuilder.equal(calculatedroutesEntity.get("storeEntity").get("pk"), s);
            predicates.add(criteriaBuilder.or(predicate));
             	
            }
       
        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
        criteriaQuery.orderBy(criteriaBuilder.asc(calculatedroutesEntity.get("routeDistance")));
        return entityManager.createQuery(criteriaQuery).setFirstResult(0).setMaxResults(10).getResultList();
          
	}
		public void saveDistances(List<CalculatedroutesEntity> calculatedroutesEntities){
			CriteriaQuery<CalculatedroutesEntity> criteriaQuery = criteriaBuilder.createQuery(CalculatedroutesEntity.class);
	        Root<CalculatedroutesEntity> calculatedroutesEntity = criteriaQuery.from(CalculatedroutesEntity.class);

		
		}
	}