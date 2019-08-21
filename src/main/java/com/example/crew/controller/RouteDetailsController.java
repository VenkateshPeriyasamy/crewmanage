package com.example.crew.controller;


import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.crew.model.RouteDetails;
import com.example.crew.repo.RouteDetailsRepo;

@RequestMapping("")
@Controller
public class RouteDetailsController {

	@Autowired
	RouteDetailsRepo routedetailsrepo;
	
	
	
	@RequestMapping(value = { "/routemaster" }, method = { RequestMethod.POST, RequestMethod.GET })
	public String product(Model model, @PageableDefault(size = 3) Pageable pageable,
			@ModelAttribute("route") @Validated RouteDetails route,
			BindingResult result,final RedirectAttributes redirectAttributes) { 	
		String records = "";
		Page<RouteDetails> page = routedetailsrepo.findAll(pageable);
		model.addAttribute("page", page);
		return "routemaster";
	}
	
	
	
	@RequestMapping(value = { "/routemastersave" }, method = { RequestMethod.POST, RequestMethod.GET })
	public String productSave(Model model, @PageableDefault(size = 3) Pageable pageable,

			@ModelAttribute("route") @Validated RouteDetails route, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		if (route != null) {
			
			routedetailsrepo.save(route); 
		}
		Page<RouteDetails> page = routedetailsrepo.findAll(pageable);
		model.addAttribute("page", page);
		return "routemaster";
	}
	
	
	 
	
}

