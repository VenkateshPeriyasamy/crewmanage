package com.example.crew.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.crew.model.CrewDetails;
import com.example.crew.model.User;
import com.example.crew.repo.CrewDetailsRepo;
import com.example.crew.repo.UserRepo;
@Controller
public class UserController {
    @Autowired
    UserRepo userrepo;
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String getSignupForm(User user) {
        return "signup";
    }
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(@ModelAttribute @Valid User user, BindingResult result, Model model) {
        User existsUser = userrepo.findByUsername(user.getUsername());
        if (result.hasErrors()) {
            return "signup";
        }
        if (existsUser != null) {
            model.addAttribute("exists", "Oops!  There is already a user registered with the Username provided.");
            return "signup";
        } else {
            String encoded = new BCryptPasswordEncoder().encode(user.getPassword());
            user.setPassword(encoded);
            System.out.print(encoded);
            userrepo.save(user);
        }
        return "redirect:/login";
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginForm(User user) {
        return "login";
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute(name = "user") User user, Model model) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User getUser = userrepo.findByUsername(user.getUsername());
        if ((getUser.getUsername().equals(user.getUsername()))
                && (bCryptPasswordEncoder.matches(user.getPassword(), getUser.getPassword()))) {
        	
        	
        	
        	 
        	
            return "redirect:/index";
        } else {
            model.addAttribute("error", "Wrong Username or Password");
            return "login";
        }
    }
    
	
	  @Autowired CrewDetailsRepo crewrepo;
	  
	  @GetMapping(value= {"userlogin"}) 
	  public String currentUser(@ModelAttribute("user") @Valid CrewDetails details, BindingResult  result, Model model,org.springframework.security.core.Authentication auth) {
	  
	  System.out.println(auth.getName());
	  
	  List<CrewDetails> crews=crewrepo.findByName(auth.getName());
	 
	  System.out.println(crews);	
	 
		  return "index";
}
}
