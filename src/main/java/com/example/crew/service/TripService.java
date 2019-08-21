package com.example.crew.service;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.crew.model.TripDetails;
import com.example.crew.repo.TripDetailsRepo;

@Service
public class TripService {
	
	@Autowired
	TripDetailsRepo triprepo;

	public Page<TripDetails> listall(Pageable pageable,int status) {
		return triprepo.findByStatus(pageable,status);
	}

	public void save(@Valid TripDetails trip) {
		triprepo.save(trip);
	}
	
	public List<TripDetails> findDistinctByDateBetween(String startdate,String enddate){
		
		
	return triprepo.findDistinctByDateBetween(startdate, enddate);	
	}

}
