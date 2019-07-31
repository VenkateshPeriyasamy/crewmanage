package com.example.crew.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.crew.model.RouteDetails;
import com.example.crew.model.TripDetails;
import com.example.crew.repo.RouteDetailsRepo;
import com.example.crew.repo.TripDetailsRepo;

@RequestMapping("")
@Controller
public class TripDetailsController {
	
	private final TripDetailsRepo tripdetailsrepo;
	
	 RouteDetailsRepo routedetailsrepo;

	public TripDetailsController(TripDetailsRepo tripdetailsrepo) {
		super();
		this.tripdetailsrepo = tripdetailsrepo;
	}

	@RequestMapping(path="/tripdetails",method=RequestMethod.POST)
	
	@ResponseBody
	
	public TripDetails tripdetails(String tripname,String departure,Date date,String arrival ) {
		TripDetails trip=tripdetailsrepo.save(new TripDetails(tripname,departure,date,arrival));
				return trip;
				
				
				
	}
}