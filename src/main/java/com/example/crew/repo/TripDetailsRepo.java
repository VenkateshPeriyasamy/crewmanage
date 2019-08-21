package com.example.crew.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crew.model.RouteDetails;
import com.example.crew.model.TripDetails;

public interface TripDetailsRepo extends JpaRepository<TripDetails,Long> {

	/* TripDetails findByRoutedetails(Long routeid); */
	
	List<TripDetails> findDistinctByDateBetween(String startdate,String enddate);

	TripDetails findByTripno(String id);
	
	Page<TripDetails> findByStatus(Pageable page,int status);

	
	

}
