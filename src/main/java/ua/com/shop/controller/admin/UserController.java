package ua.com.shop.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ua.com.shop.entity.Type;
import ua.com.shop.entity.User;
import ua.com.shop.service.UserService;
import ua.com.shop.validator.UserValidator;

@Controller
@RequestMapping("/admin/user")
@SessionAttributes("user")
public class UserController {

	@Autowired
	private UserService userService;

	@InitBinder("user")
	protected void bind(WebDataBinder binder) {
		binder.setValidator(new UserValidator(userService));
	}
	@ModelAttribute("user")
	public User getForm() {
		return new User();
	}
	@GetMapping
	public String show(Model model){
		model.addAttribute("users", userService.findAll());
		return "admin-user";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable int id, Model model){
		model.addAttribute("user", userService.findOne(id));
		show(model);
		return "admin-user";
	}
	
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id){
		userService.delete(id);
		return "redirect:/admin/user";
	}
	
	@PostMapping
	public String save(@ModelAttribute("user")@Valid User user, BindingResult br , Model model, SessionStatus status ){
		if(br.hasErrors()){
			return show(model);
		}
		userService.save(user);
		status.setComplete();
		return "redirect:/admin/user";
	}
}
