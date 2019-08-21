package com.example.crew.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.crew.model.FlightDetails;
import com.example.crew.repo.FlightDetailsRepo;
import com.example.crew.service.FlightDetailsService;
@Controller
public class FlightDetailsController {
    
    @Autowired
    FlightDetailsRepo flightrepo;
    
    @Autowired
      FlightDetailsService service;
    
    
    @RequestMapping(value="/flightmaster",method=RequestMethod.GET)
    public String getFlightMaster(Model model,FlightDetails flightDetails,@PageableDefault(size = 3) Pageable pageable) {
    	Page<FlightDetails> page =flightrepo.findAll(pageable);
        List<FlightDetails> fdetails=service.listall();
        model.addAttribute("flightrecods", fdetails);
        model.addAttribute("page",page);
        return "flightmaster";
    }
    
    @RequestMapping(value="/flightsave",method=RequestMethod.POST)
    public String addFlight(@ModelAttribute(name="flightDetails") @Valid FlightDetails flightDetails,@PageableDefault(size = 3) Pageable pageable,BindingResult result,Model model,final RedirectAttributes redirectAttributes) {
        
//        if (result.hasErrors()) {
//            return "flightmaster";
//        }
        
        service.save(flightDetails);
        Page<FlightDetails> page =flightrepo.findAll(pageable);

        List<FlightDetails> fdetails=service.listall();
        model.addAttribute("flightrecods",fdetails);
        model.addAttribute("page",page);
        return "redirect:/flightmaster";
        
    }
    @RequestMapping("/edit/{id}")
    public ModelAndView showEditPage(@PathVariable(name="id") Long id) {
        ModelAndView mav=new ModelAndView("edit");
        FlightDetails flight=service.get(id);
        mav.addObject("flightDetails",flight);
        return mav;
    }
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable(name="id") Long id) {
        service.delete(id);
        return "redirect:/flightmaster";
        
    }
    @RequestMapping("/findOne")
    @ResponseBody
    public FlightDetails findOne(Long id) {
        return service.findOne(id);
    }
    
}