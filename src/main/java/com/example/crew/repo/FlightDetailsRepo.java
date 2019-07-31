package com.example.crew.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crew.model.FlightDetails;

public interface FlightDetailsRepo extends JpaRepository<FlightDetails,Long>{


}
