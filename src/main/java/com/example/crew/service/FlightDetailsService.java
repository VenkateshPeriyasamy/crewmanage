package com.example.crew.service;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crew.model.FlightDetails;
import com.example.crew.repo.FlightDetailsRepo;

@Service
public class FlightDetailsService {
    
    @Autowired
    FlightDetailsRepo repo;
    
    public List<FlightDetails> listall() {        
        return repo.findAll();
    }
    public void save(@Valid FlightDetails flightDetails) {
        repo.save(flightDetails);
        
    }
    
    public FlightDetails get(Long id) {
        return repo.findById(id).get();
    }
    
    public void delete(Long id) {
        repo.deleteById(id);
    }
    public FlightDetails findOne(Long id) {
        
        return repo.findById(id).get();
    }
    
    
}
