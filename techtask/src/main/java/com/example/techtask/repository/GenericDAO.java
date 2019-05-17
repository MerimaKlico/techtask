package com.example.techtask.repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.core.GenericTypeResolver;
import org.springframework.transaction.annotation.Transactional;



@Transactional
public abstract class GenericDAO<T, K extends Serializable> {

    protected EntityManager entityManager;
    protected CriteriaBuilder criteriaBuilder;

    private final Class<T> genericType;

    @SuppressWarnings("unchecked")
    public GenericDAO() {
    	Class<?>[] genericTypes = GenericTypeResolver.resolveTypeArguments(getClass(), GenericDAO.class);
        genericType = (Class<T>) genericTypes[0];
        
        //provjeriti zasto ono nije radilo sa 
        //Expected 1 type argument on generic interface but found 2
    }
    
    public T save(final T entity) {
        entityManager.persist(entity);
        return entity;
    }

 
    public void delete(final K pk) {
    	CriteriaDelete<T> criteriaDelete = criteriaBuilder.createCriteriaDelete(genericType);
        Root<T> root = criteriaDelete.from(genericType);

        criteriaDelete.where(criteriaBuilder.equal(root.get("pk"), pk));
        entityManager.createQuery(criteriaDelete).executeUpdate();
    	/*TypedQuery<T> query = entityManager.createQuery("delete from " + genericType.getName() + " where pk= "+pk, genericType); */
    }

    public void update(final T entity) {
        entityManager.merge(entity);
    }

    public T findByPK(final K pk) {
        return entityManager.find(genericType, pk);
    }


    public List<T> findAll() {
    	
    	CriteriaQuery<T> query = criteriaBuilder.createQuery(genericType);
        Root<T> root = query.from(genericType);
        
        query.select(root);
        
        return entityManager.createQuery(query).getResultList();
        //TypedQuery<T> query = entityManager.createQuery("from " + genericType.getName(), genericType);
       // return query.getResultList();
    }
    
    
    @PersistenceContext
    public void setEntityManager(final EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

 }
