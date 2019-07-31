package com.example.crew.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crew.model.RouteDetails;

public interface RouteDetailsRepo extends JpaRepository<RouteDetails,Long>{
	RouteDetails findByrouteid(long id);

}
