package com.example.crew.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.crew.config.StaticUtils;
import com.example.crew.model.CrewDetails;
import com.example.crew.model.FlightDetails;
import com.example.crew.model.RouteDetails;
import com.example.crew.model.TripDetails;
import com.example.crew.model.TripHistory;
import com.example.crew.repo.CrewDetailsRepo;
import com.example.crew.repo.RouteDetailsRepo;
import com.example.crew.repo.TripDetailsRepo;

import com.example.crew.repo.triphistoryrepo;
import com.example.crew.service.FlightDetailsService;
import com.example.crew.service.TripService;

@RequestMapping("")
@Controller
public class TripDetailsController {
	@Autowired
	TripService tripservice;

	@Autowired
	RouteDetailsRepo routerepo;

	@Autowired
	FlightDetailsService flightservice;

	@Autowired
	CrewDetailsRepo crewrepo;

	@Autowired
	TripDetailsRepo triprepo;

	@Autowired
	triphistoryrepo tickethistoryrepo;

	@RequestMapping(value = "/tripmaster", method = { RequestMethod.POST, RequestMethod.GET })
	public String getTripMaster(Model model, @ModelAttribute(name = "trip") @Valid TripDetails tripDetails,
			@PageableDefault(size = 4) Pageable pageable) {

		Page<TripDetails> page = tripservice.listall(pageable, 0);
		System.out.print(page);
		model.addAttribute("page", page);

		List<RouteDetails> routedetails = routerepo.findAll();
		model.addAttribute("routes", routedetails);

		List<FlightDetails> flightdetails = flightservice.listall();
		model.addAttribute("flights", flightdetails);

		List<CrewDetails> crewdetails = crewrepo.findAll();
		model.addAttribute("crews", crewdetails);

		// model.addAttribute("temp", "show");
		return "tripmaster";

	}

	@RequestMapping(value = "/addtrip", method = RequestMethod.POST)
	public String addTrip(Model model, @Validated TripDetails trip, BindingResult result,
			@PageableDefault(size = 4) Pageable pageable) {

		trip.setStatus(0);
		tripservice.save(trip);

		Page<TripDetails> page = tripservice.listall(pageable, 0);
		model.addAttribute("page", page);

		return "redirect:/tripmaster";

	}

	@RequestMapping(value = "/loadcrewdetails", method = RequestMethod.POST)
	public String loadcrewdetailsonavailability(Model model,
			@ModelAttribute(name = "trip") @Valid TripDetails tripDetails, BindingResult result,
			@PageableDefault(size = 4) Pageable pageable) {

		Page<TripDetails> page = tripservice.listall(pageable, 0);
		System.out.print(page);
		model.addAttribute("page", page);

		List<RouteDetails> routedetails = routerepo.findAll();
		model.addAttribute("routes", routedetails);

		List<FlightDetails> flightdetails = flightservice.listall();
		model.addAttribute("flights", flightdetails);

		List<CrewDetails> crewDetails = new ArrayList<CrewDetails>();

		List<String> crewids = new ArrayList<String>();
		List<TripDetails> listoftrips = tripservice.findDistinctByDateBetween(tripDetails.getDate(),
				tripDetails.getDate());

		System.out.println("trip size:-" + listoftrips.size());
		listoftrips.forEach(action -> {
			action.getCrewdetails().forEach(crewnember -> {

				crewids.add(crewnember.getMemberid());

			});
		});

		/*
		 * for (CrewDetails pilot : crewpilot) { boolean found = false; for (String id :
		 * crewids) { if (pilot.getId() == Integer.parseInt(id)) { found = true; } }
		 * if(!found) { crewpilotavailable.add(pilot); } }
		 */

		System.out.println(crewids.toString());
		if (crewids.isEmpty()) {
			crewDetails = crewrepo.findAll();
		} else {
			crewDetails = crewrepo.findByMemberidNotIn(crewids);
		}

		System.out.println("crewdetails:" + crewDetails);

		model.addAttribute("crews", crewDetails);

		model.addAttribute("temp", "show");

		return "tripmaster";

	}

	@RequestMapping(value = "/tripedit", method = { RequestMethod.POST, RequestMethod.GET })
	public String tripedit(@ModelAttribute("trip") @Valid TripDetails trip, Model model, BindingResult result,

			@PageableDefault(size = 4) Pageable pageable, HttpServletRequest request)
			throws IllegalStateException, IOException {

		String id = request.getParameter("tripno");
		TripDetails tripedit = triprepo.findByTripno(id);
		if (tripedit != null) {

			tripedit.setDate(request.getParameter("date"));
			tripedit.setDeparture(request.getParameter("departure"));
			tripedit.setArrival(request.getParameter("arrival"));

			triprepo.save(tripedit);

		}

		Page<TripDetails> page = triprepo.findAll(pageable);

		model.addAttribute("page", page);
		return "redirect:/tripmaster";

	}

	@RequestMapping(value = "/tripupdateall", method = { RequestMethod.POST, RequestMethod.GET })
	public String tripupdate() throws IllegalStateException, IOException, ParseException {

		List<TripDetails> getUsers = triprepo.findAll();

		getUsers.forEach(getUser -> {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
			Date date1 = new Date();
			try {

				date1 = sdf.parse(getUser.getDate() + " " + getUser.getArrival());
				System.out.println("date parsed:" + date1);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (new Date().compareTo(date1) > 0) {
				getUser.setStatus(1);
				triprepo.save(getUser);
				tickethistoryrepo.save(new TripHistory(getUser));
				System.out.println(date1 + ":changes done");
			} else {
				System.out.println(date1 + ":no changes");
			}
		});
		return null;

	}
	
	
	@RequestMapping(value = "/triphistory", method = { RequestMethod.POST, RequestMethod.GET })
	public String getTriphistory(Model model, @ModelAttribute(name = "history") @Valid TripHistory triphistory,
			@PageableDefault(size = 4) Pageable pageable) {

		
		/*
		 * Page<TripDetails> page = tripservice.listall(pageable, 0);
		 * System.out.print(page); model.addAttribute("page", page);
		 */
		 
		
		 Page<TripHistory> history=tickethistoryrepo.findAll(pageable);
		 model.addAttribute("page",history);
		 
		
		
		/*
		 * List<RouteDetails> routedetails = routerepo.findAll();
		 * model.addAttribute("routes", routedetails);
		 * 
		 * List<FlightDetails> flightdetails = flightservice.listall();
		 * model.addAttribute("flights", flightdetails);
		 * 
		 * List<CrewDetails> crewdetails = crewrepo.findAll();
		 * model.addAttribute("crews", crewdetails);
		 */

		// model.addAttribute("temp", "show");
		return "triphistory";

	}

}