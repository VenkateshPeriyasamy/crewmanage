package com.example.crew.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crew.model.CrewDetails;
import com.example.crew.model.FlightDetails;
import com.example.crew.model.User;

public interface CrewDetailsRepo extends JpaRepository<CrewDetails,Long> {
	
	List<CrewDetails> findByMemberidNotIn(List<String> crewids);

	List<CrewDetails> findByDesignation(String string);

	CrewDetails findByMemberid(String id);

	List<CrewDetails> findByName(String name);

	List<CrewDetails> findById(String name);

	
}
