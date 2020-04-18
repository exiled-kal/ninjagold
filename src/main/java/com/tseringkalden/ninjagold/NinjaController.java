package com.tseringkalden.ninjagold;

import java.util.ArrayList;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@Controller
public class NinjaController {
//	creating arraylist for our gold to pop message
	public ArrayList<String> activities = new ArrayList<String>(); 
  
//session is dict in java ie key and value
	@GetMapping("/Gold")
	public String root(Model model, HttpSession session) {
		if (session.getAttribute("gold") == null) {
		    session.setAttribute("gold",0);
		}
		
		model.addAttribute("activities", activities);
		
		
		return "ninjas.jsp";
	}
	
	@PostMapping("/")
	public String process(@RequestBody() String postData, HttpSession session) {
		
		String location = postData.substring(0, postData.indexOf("="));
		Random random = new Random();
		int n = 0;
		
//		turning object into an int
		int gold = (int) session.getAttribute("gold");
		String word = "You have entered a";
//		word variable holds value of gold every time there is changes in the num of gold
		
		
//		switch is identical to if statement
		switch(location) {
		case "casino":
			n = random.nextInt(50);
			break;
		case "farm":
			n = random.nextInt(10)+10;
			break;
		case "house":
			n = random.nextInt(3)+2;
			break;
		case "cave":
			n = random.nextInt(5)+5;
			break;
		}
		
		word += location+" and earned gold"+ gold;
		gold += n;
		
		session.setAttribute("gold", gold);
		
		activities.add(word);
		
	
		return "redirect:/";
	}
	
	
	
}
