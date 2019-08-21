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
	private String routeno;
	@Column
	private String routename;
	
	@Column
	private String departure;
	
	@Column
	private String arrival;
	
	@Column
	private String duration;
	
	
	
	
	public RouteDetails() {
		
	}




	public Long getRouteid() {
		return routeid;
	}




	public void setRouteid(Long routeid) {
		this.routeid = routeid;
	}




	public String getRouteno() {
		return routeno;
	}




	public void setRouteno(String routeno) {
		this.routeno = routeno;
	}




	public String getRoutename() {
		return routename;
	}




	public void setRoutename(String routename) {
		this.routename = routename;
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




	public String getDuration() {
		return duration;
	}




	public void setDuration(String duration) {
		this.duration = duration;
	}




	@Override
	public String toString() {
		return "RouteDetails [routeid=" + routeid + ", routeno=" + routeno + ", routename=" + routename + ", departure="
				+ departure + ", arrival=" + arrival + ", duration=" + duration + "]";
	}




	public RouteDetails(Long routeid, String routeno, String routename, String departure, String arrival,
			String duration) {
		super();
		this.routeid = routeid;
		this.routeno = routeno;
		this.routename = routename;
		this.departure = departure;
		this.arrival = arrival;
		this.duration = duration;
	}
	
	
}
