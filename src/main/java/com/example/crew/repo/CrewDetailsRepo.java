package com.example.crew.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crew.model.CrewDetails;
import com.example.crew.model.FlightDetails;

public interface CrewDetailsRepo extends JpaRepository<CrewDetails,Long> {

	CrewDetails findByMemberid(String i);
	//Optional<CrewDetails> findById(Long id);
}
