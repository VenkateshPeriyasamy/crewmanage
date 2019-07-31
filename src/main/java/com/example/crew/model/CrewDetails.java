package com.example.crew.model;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;



@Table
@Entity
public class CrewDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="memberid",length=30)
	private String memberid;
    /*@Size(min=3,max=30)*/
	private String name;
	
	//private String firstname;
	
	//private String lastname;

	private String gender;
 

	private String dob;
	
	private String email;

	private String designation;

	private String doj;

	private String location;
	
	private String mobno;
	
	private String edu;
	
	private String exp;
	
	private byte[] image;
	
	private String filename;

	
	
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public CrewDetails(Long id, String memberid, String name, String gender, String dob, String email,
			String designation, String doj, String location, String mobno, String edu, String exp, byte[] image,
			Set<TripDetails> tripdetails) {
		super();
		this.id = id;
		this.memberid = memberid;
		this.name = name;
		this.gender = gender;
		this.dob = dob;
		this.email = email;
		this.designation = designation;
		this.doj = doj;
		this.location = location;
		this.mobno = mobno;
		this.edu = edu;
		this.exp = exp;
		this.image = image;
		this.tripdetails = tripdetails;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getEdu() {
		return edu;
	}

	public void setEdu(String edu) {
		this.edu = edu;
	}

	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	public String getMobno() {
		return mobno;
	}

	public void setMobno(String mobno) {
		this.mobno = mobno;
	}

	@ManyToMany(mappedBy = "crewdetails")
	private Set<TripDetails> tripdetails = new HashSet<>();

	

	public Set<TripDetails> getTripdetails() {
		return tripdetails;
	}

	public void setTripdetails(Set<TripDetails> tripdetails) {
		this.tripdetails = tripdetails;
	}

	public CrewDetails() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDoj() {
		return doj;
	}

	public void setDoj(String doj) {
		this.doj = doj;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}


	
	

	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	

}
