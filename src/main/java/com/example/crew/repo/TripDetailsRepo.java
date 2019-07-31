package com.example.crew.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crew.model.RouteDetails;
import com.example.crew.model.TripDetails;

public interface TripDetailsRepo extends JpaRepository<TripDetails,Long> {

	/* TripDetails findByRoutedetails(Long routeid); */
	

}
