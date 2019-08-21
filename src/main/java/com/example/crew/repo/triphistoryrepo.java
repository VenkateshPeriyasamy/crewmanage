package com.example.crew.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.crew.model.RouteDetails;
import com.example.crew.model.TripHistory;

@Repository
public interface triphistoryrepo extends JpaRepository<TripHistory,Long>{

}
