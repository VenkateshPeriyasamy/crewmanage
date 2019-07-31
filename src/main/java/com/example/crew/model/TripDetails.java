 package com.example.crew.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Table
@Entity
public class TripDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long tripid;
	@Column
	private String tripname;
	@Column
	private String departure;
	@Column
	private Date date;
	@Column
	private String arrival;
	
	

	/*
	 * @Temporal(value = null) private String temprecord;
	 */

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "routetripid", referencedColumnName = "routeid")
	private RouteDetails routedetails;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "flightid", referencedColumnName = "id")
	private FlightDetails flightdetails;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "trip_crewdetails", joinColumns = {
			@JoinColumn(name = "trip_id", nullable = false, updatable = true) }, inverseJoinColumns = {
					@JoinColumn(name = "crewid_fk", nullable = false, updatable = true) })
	Set<CrewDetails> crewdetails = new HashSet<>();

	public Set<CrewDetails> getCrewdetails() {
		return crewdetails;
	}

	public void setCrewdetails(Set<CrewDetails> crewdetails) {
		this.crewdetails = crewdetails;
	}

	public FlightDetails getFlightdetails() {
		return flightdetails;
	}

	public void setFlightdetails(FlightDetails flightdetails) {
		this.flightdetails = flightdetails;
	}

	public RouteDetails getRoutedetails() {
		return routedetails;
	}

	public void setRoutedetails(RouteDetails routedetails) {
		this.routedetails = routedetails;
	}

	public TripDetails() {

	}

	@Override
	public String toString() {
		return "TripDetails [tripid=" + tripid + ", tripname=" + tripname + ", departure=" + departure + ", date="
				+ date + ", arrival=" + arrival + "]";
	}

	public TripDetails(String tripname, String departure, Date date, String arrival) {
		super();

		this.tripname = tripname;
		this.departure = departure;
		this.date = date;
		this.arrival = arrival;
	}

	public Long getTripid() {
		return tripid;
	}

	public void setTripid(Long tripid) {
		this.tripid = tripid;
	}

	public String getTripname() {
		return tripname;
	}

	public void setTripname(String tripname) {
		this.tripname = tripname;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

}
