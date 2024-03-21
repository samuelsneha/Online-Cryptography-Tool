package com.cgtmse.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;

import com.cgtmse.entity.YourActivity;

@Qualifier("criteriaRepository")
public interface CriteriaRepository {
	
	List<YourActivity> getAllData( String email );

}
