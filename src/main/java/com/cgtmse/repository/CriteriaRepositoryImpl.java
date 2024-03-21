package com.cgtmse.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cgtmse.entity.YourActivity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class CriteriaRepositoryImpl implements CriteriaRepository{
	
	@Autowired
	EntityManager em;
	
	@Override
	public List<YourActivity> getAllData( String emailValue ){
		TypedQuery<YourActivity> query = null;
		try {
			System.out.println(" reached criteria repository");
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<YourActivity> cq = cb.createQuery(YourActivity.class);
			Root<YourActivity> yourActivity = cq.from(YourActivity.class);
			Predicate predicate1 = cb.equal(yourActivity.get("email"), emailValue);
			cq.where(predicate1);
			query = em.createQuery(cq);
		}catch( Exception e ) {
			System.out.println("Exception at criteria repository is "+e.getMessage());
		}
		return query.getResultList();
	}
	

	
}
