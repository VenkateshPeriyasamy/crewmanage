package com.example.crew.model;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
public class RouteDetails {

	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private Long routeid;
	@Column
	private String routename;
	
	@Column
	private String departure;
	
	@Column
	private String arrival;
	
	@Column
	private Time duration;
	
	
	
	
	public RouteDetails() {
		
	}
	
	public RouteDetails(String routename, String departure, String arrival, Time duration) {
		super();
		this.routename = routename;
		this.departure = departure;
		this.arrival = arrival;
		this.duration = duration;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public Time getDuration() {
		return duration;
	}

	public void setDuration(Time duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "RouteDetails [routeid=" + routeid + ", routename=" + routename + ", departure=" + departure
				+ ", arrival=" + arrival + ", duration=" + duration + "]";
	}

	public RouteDetails( String routename) {
		super();
		
		this.routename = routename;
	}

	public Long getRouteid() {
		return routeid;
	}

	public void setRouteid(Long routeid) {
		this.routeid = routeid;
	}

	public String getRoutename() {
		return routename;
	}

	public void setRoutename(String routename) {
		this.routename = routename;
	}
}
