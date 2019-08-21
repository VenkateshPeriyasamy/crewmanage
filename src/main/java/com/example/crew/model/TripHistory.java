package com.example.crew.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table
@Entity
public class TripHistory {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long triphistoryid;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "historyid", referencedColumnName = "tripid")
	private TripDetails tripdetails;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "crewid", referencedColumnName = "id")
	private CrewDetails crewdetails;
	
	
	public CrewDetails getCrewdetails() {
		return crewdetails;
	}

	public void setCrewdetails(CrewDetails crewdetails) {
		this.crewdetails = crewdetails;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "routetripid", referencedColumnName = "routeid")
	private RouteDetails routedetails;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "flightid", referencedColumnName = "id")
	private FlightDetails flightdetails;

	
	public RouteDetails getRoutedetails() {
		return routedetails;
	}

	public void setRoutedetails(RouteDetails routedetails) {
		this.routedetails = routedetails;
	}

	public FlightDetails getFlightdetails() {
		return flightdetails;
	}

	public void setFlightdetails(FlightDetails flightdetails) {
		this.flightdetails = flightdetails;
	}

	public Long getTriphistoryid() {
		return triphistoryid;
	}

	public void setTriphistoryid(Long triphistoryid) {
		this.triphistoryid = triphistoryid;
	}

	public TripDetails getTripdetails() {
		return tripdetails;
	}

	public void setTripdetails(TripDetails tripdetails) {
		this.tripdetails = tripdetails;
	}

	public TripHistory(Long triphistoryid, TripDetails tripdetails) {
		super();
		this.triphistoryid = triphistoryid;
		this.tripdetails = tripdetails;
	}

	public TripHistory(TripDetails tripdetails) {
		super();
		this.tripdetails = tripdetails;
	}

	public TripHistory() {
		
	}
	
	
}
