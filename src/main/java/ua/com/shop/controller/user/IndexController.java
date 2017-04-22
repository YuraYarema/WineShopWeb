package ua.com.shop.controller.user;

import java.security.Principal;
import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
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

import ua.com.shop.dto.filter.WineFilter;
import ua.com.shop.dto.form.WineForm;
import ua.com.shop.entity.Orders;
import ua.com.shop.entity.User;
import ua.com.shop.entity.Wine;
import ua.com.shop.service.AlcoholContentService;
import ua.com.shop.service.CapacityService;
import ua.com.shop.service.CountryService;
import ua.com.shop.service.MakerService;
import ua.com.shop.service.OrdersService;
import ua.com.shop.service.TypeService;
import ua.com.shop.service.UserService;
import ua.com.shop.service.WineService;
import ua.com.shop.service.YearService;
import ua.com.shop.validator.UserValidator;

@Controller
@RequestMapping("/")
@SessionAttributes("order")
public class IndexController {

	@Autowired
	private WineService wineService;

	@Autowired
	private MakerService makerService;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private TypeService typeService;

	@Autowired
	private CapacityService capacityService;

	@Autowired
	private YearService yearService;

	@Autowired
	private AlcoholContentService alcoholContentService;
	
	@Autowired
	private OrdersService ordersService;

	@ModelAttribute("wine")
	public Wine getForm() {
		return new Wine();
	}
	@ModelAttribute("order")
	public Orders getOrders() {
		return new Orders();
	}

	@ModelAttribute("filter")
	public WineFilter getFilter() {
		return new WineFilter();
	}
	
	@GetMapping
	public String index(Principal principal, Model model,
			@PageableDefault(size = 5) Pageable pageable,
			@ModelAttribute("order") Orders order,
			@ModelAttribute("filter") WineFilter filter) {
		if (principal != null) {
			System.out.println(principal.getName());
			SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
		}
		return show(model, pageable,order, filter);
	}
	
	@InitBinder("user")
	protected void bind(WebDataBinder binder) {
		binder.setValidator(new UserValidator(userService));
	}

	@RequestMapping
	public String show(Model model, @PageableDefault Pageable pageable,
			@ModelAttribute("order") Orders order,
			@ModelAttribute("filter") WineFilter filter) {
		model.addAttribute("ord", order);
		model.addAttribute("page", wineService.findAll(pageable, filter));
		model.addAttribute("ACS", alcoholContentService.findAll());
		model.addAttribute("types", typeService.findAll());
		model.addAttribute("years", yearService.findAll());
		model.addAttribute("countrys", countryService.findAll());
		model.addAttribute("makers", makerService.findAll());
		model.addAttribute("capacitys", capacityService.findAll());	
		return "user-index";
	}
	
	@RequestMapping("/maker/{id}")
	public String maker(@PathVariable int id,Model model){
		model.addAttribute("maker", makerService.findOne(id));
		model.addAttribute("winess", wineService.findByWineId(id));
		model.addAttribute("countrys", countryService.findAll());
		model.addAttribute("makers", makerService.findAll());
		model.addAttribute("types", typeService.findAll());
		return "user-maker";
	}
	@RequestMapping("/country/{id}")
	public String country(@PathVariable int id,Model model){
		model.addAttribute("country", countryService.findOne(id));
		model.addAttribute("winess", wineService.findByCountryId(id));
		model.addAttribute("countrys", countryService.findAll());
		model.addAttribute("makers", makerService.findAll());
		model.addAttribute("types", typeService.findAll());
		return "user-country";
	}
	@RequestMapping("/type/{id}")
	public String type(@PathVariable int id,Model model){
		model.addAttribute("type", typeService.findOne(id));
		model.addAttribute("winess", wineService.findByTypeId(id));
		model.addAttribute("countrys", countryService.findAll());
		model.addAttribute("makers", makerService.findAll());
		model.addAttribute("types", typeService.findAll());
		return "user-type";
	}
	
	@RequestMapping("/wine/{id}")
	public String wine(@PathVariable int id,Model model){
		model.addAttribute("wine", wineService.findOne(id));
		model.addAttribute("winess",  wineService.findByCategoriId(id));
		model.addAttribute("countrys", countryService.findAll());
		model.addAttribute("makers", makerService.findAll());
		model.addAttribute("types", typeService.findAll());
		return "user-wine";
	}
	@GetMapping("/registration")
	public String registration(Model model){
		model.addAttribute("user", new User());
		model.addAttribute("countrys", countryService.findAll());
		model.addAttribute("makers", makerService.findAll());
		model.addAttribute("types", typeService.findAll());
		return "user-registration";
	}
	
	@PostMapping("/registration")
	public String save(@ModelAttribute("user") @Valid User user,
			BindingResult br) {
		if (br.hasErrors()){
			return "user-registration";
		}
		userService.save(user);
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String login(Model model){
		model.addAttribute("countrys", countryService.findAll());
		model.addAttribute("makers", makerService.findAll());
		model.addAttribute("types", typeService.findAll());
		return "user-login";
	}
	@GetMapping("/order")
	public String order(Model model){
		model.addAttribute("countrys", countryService.findAll());
		model.addAttribute("makers", makerService.findAll());
		model.addAttribute("types", typeService.findAll());
		return "user-order";
	}
	@PostMapping("/addtocart/{id}")
	public String addToCart(Model model, @PageableDefault Pageable pageable,
			@PathVariable int id, @ModelAttribute("order") Orders order,
			@ModelAttribute("filter") WineFilter filter) {
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		order.setUser(user);
		Wine wine = wineService.findOne(id);
		if (order.getWines() == null)
			order.setWines(new ArrayList<Wine>());
			order.getWines().add(wine);
		return show(model, pageable, order, filter);
	}
	@GetMapping("/makeorder")
	public String makeOrder(@ModelAttribute("order") Orders order,
			SessionStatus status) {
		if (order.getWines() != null && !order.getWines().isEmpty())
			ordersService.save(order);
		status.setComplete();
		return "redirect:/order";
	}
	
	@PostMapping("/deletefromcart/{id}")
	public String deleteFromCart(Model model, @PathVariable int id,
			@ModelAttribute("order") Orders order) {
		order.getWines().remove(wineService.findOne(id));
		return "redirect:/shoppingcart";
	}

	@RequestMapping("/shoppingcart")
	public String shoppingcart(Model model,
			@ModelAttribute("order") Orders order,@ModelAttribute("wine") Wine wine,Principal principal) {
		model.addAttribute("qwer", order.getWines());
		model.addAttribute("countrys", countryService.findAll());
		model.addAttribute("makers", makerService.findAll());
		model.addAttribute("types", typeService.findAll());
		return "user-shoppingcart";
	}

}
