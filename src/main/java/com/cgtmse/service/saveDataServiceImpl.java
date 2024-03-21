package com.cgtmse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgtmse.entity.YourActivity;
import com.cgtmse.repository.CriteriaRepository;
import com.cgtmse.repository.saveDataRepository;

@Service
public class saveDataServiceImpl implements saveDataService{
	
	@Autowired
	saveDataRepository savedatarepository;
    
	@Autowired
	CriteriaRepository criteriarepository;
	
	@Override
	public YourActivity saveData( YourActivity yourActivity ) {
		if(yourActivity != null ) {
			YourActivity yourActivityNew = savedatarepository.save(yourActivity);		
			return yourActivityNew;
		}else {
			System.out.println("error in saving data");
			return null;
		}
	}
	
	@Override
	public List<YourActivity> showTable( String email ) {
		System.out.println("email in saveData service class "+email);
		List<YourActivity> res = criteriarepository.getAllData(email);
		System.out.println( res.size());
		for( YourActivity obj : res ) {
			System.out.println( obj.getEmail());
			System.out.println( obj.getPlainTextMessage());
			System.out.println( obj.getEncryptedMessage());
			System.out.println( obj.getDecryptedMessage());
		}
		return res;
	}
	
	

}
