       package com.example.crew.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table
public class FlightDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;
    
    
    @Column
    private String airline;
    
    
    @Column
    private String flightType;
    
    
    @Column
    private String flightCapacity;
    
    
    @Column
    private String flightModelNo;
    
    
    @Column
    private String flightNo;
    
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getAirline() {
        return airline;
    }
    public void setAirline(String airline) {
        this.airline = airline;
    }
    public String getFlightType() {
        return flightType;
    }
    public void setFlightType(String flightType) {
        this.flightType = flightType;
    }
    public String getFlightCapacity() {
        return flightCapacity;
    }
    public void setFlightCapacity(String flightCapacity) {
        this.flightCapacity = flightCapacity;
    }
    public String getFlightModelNo() {
        return flightModelNo;
    }
    public void setFlightModelNo(String flightModelNo) {
        this.flightModelNo = flightModelNo;
    }
    public String getFlightNo() {
        return flightNo;
    }
    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }
    @Override
    public String toString() {
        return "FlightDetails [id=" + id + ", airline=" + airline + ", flightType=" + flightType + ", flightCapacity="
                + flightCapacity + ", flightModelNo=" + flightModelNo + ", flightNo=" + flightNo + "]";
    }
    
    
    public FlightDetails(Long id, String airline, String flightType, String flightCapacity, String flightModelNo,
            String flightNo) {
        super();
        this.id = id;
        this.airline = airline;
        this.flightType = flightType;
        this.flightCapacity = flightCapacity;
        this.flightModelNo = flightModelNo;
        this.flightNo = flightNo;
    }
    public FlightDetails() {
        
    }
}
