package com.cgtmse.service;

import java.util.List;

import com.cgtmse.entity.YourActivity;

public interface saveDataService {
	
	YourActivity saveData( YourActivity yourActivity );
	List<YourActivity> showTable( String email);

}
