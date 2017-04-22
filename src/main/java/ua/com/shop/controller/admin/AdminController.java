package ua.com.shop.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.shop.service.CountryService;
import ua.com.shop.service.MakerService;
import ua.com.shop.service.TypeService;

@Controller
public class AdminController {
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private MakerService makerService;
	
	@Autowired
	private TypeService typeService;

	@RequestMapping("/admin")
	public String admin(Model model){
		model.addAttribute("countrys", countryService.findAll());
		model.addAttribute("makers", makerService.findAll());
		model.addAttribute("types", typeService.findAll());
		return "admin-admin";
	}
}
