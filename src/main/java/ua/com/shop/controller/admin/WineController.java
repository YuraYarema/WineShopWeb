package ua.com.shop.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import ua.com.shop.editor.AlcoholContentEditor;
import ua.com.shop.editor.CapacityEditor;
import ua.com.shop.editor.CountryEditor;
import ua.com.shop.editor.MakerEditor;
import ua.com.shop.editor.TypeEditor;
import ua.com.shop.editor.YearEditor;
import ua.com.shop.entity.AlcoholContent;
import ua.com.shop.entity.Capacity;
import ua.com.shop.entity.Country;
import ua.com.shop.entity.Maker;
import ua.com.shop.entity.Type;
import ua.com.shop.entity.Year;
import ua.com.shop.service.AlcoholContentService;
import ua.com.shop.service.CapacityService;
import ua.com.shop.service.CountryService;
import ua.com.shop.service.MakerService;
import ua.com.shop.service.TypeService;
import ua.com.shop.service.WineService;
import ua.com.shop.service.YearService;
import ua.com.shop.util.ParamBuilder;
import ua.com.shop.validator.WineValidator;

@Controller
@RequestMapping("/admin/wine")
@SessionAttributes("wine")
public class WineController {

	@Autowired
	private WineService wineService;

	@Autowired
	private MakerService makerService;

	@Autowired
	private TypeService typeService;

	@Autowired
	private CapacityService capacityService;

	@Autowired
	private YearService yearService;

	@Autowired
	private AlcoholContentService alcoholContentService;
	
	@Autowired
	private CountryService countryService;
	
	@InitBinder("wine")
	protected void bind(WebDataBinder binder) {
		binder.registerCustomEditor(Maker.class, new MakerEditor(makerService));
		binder.registerCustomEditor(Type.class, new TypeEditor(typeService));
		binder.registerCustomEditor(Country.class, new CountryEditor(countryService));
		binder.registerCustomEditor(Year.class, new YearEditor(yearService));
		binder.registerCustomEditor(Capacity.class, new CapacityEditor(
				capacityService));
		binder.registerCustomEditor(AlcoholContent.class,
				new AlcoholContentEditor(alcoholContentService));
		binder.setValidator(new WineValidator(wineService));		
	}

	@ModelAttribute("wine")
	public WineForm getForm() {
		return new WineForm();
	}

	@ModelAttribute("filter")
	public WineFilter getFilter() {
		return new WineFilter();
	}

	@RequestMapping
	public String show(Model model, @PageableDefault Pageable pageable,
			@ModelAttribute("filter") WineFilter filter) {
		model.addAttribute("page", wineService.findAll(pageable, filter));
		model.addAttribute("ACS", alcoholContentService.findAll());
		model.addAttribute("types", typeService.findAll());
		model.addAttribute("years", yearService.findAll());
		model.addAttribute("makers", makerService.findAll());
		model.addAttribute("capacitys", capacityService.findAll());
		model.addAttribute("countrys", countryService.findAll());
		return "admin-wine";
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id,
			@PageableDefault Pageable pageable,
			@ModelAttribute("filter") WineFilter filter) {
		wineService.delete(id);
		return "redirect:/admin/wine"+getParams(pageable, filter);

	}

	@GetMapping("/update/{id}")
	public String update(@PathVariable int id, Model model,
			@PageableDefault Pageable pageable,
			@ModelAttribute("filter") WineFilter filter) {
		model.addAttribute("wine", wineService.findForm(id));
		return show(model, pageable, filter);
	}

	@PostMapping
	public String save(@ModelAttribute("wine") @Valid WineForm form,
			BindingResult br, Model model, SessionStatus status,
			@PageableDefault Pageable pageable,
			@ModelAttribute("filter") WineFilter filter) {
		if (br.hasErrors()) {
			return show(model, pageable, filter);
		}
		wineService.save(form);
		status.setComplete();
		return "redirect:/admin/wine"+getParams(pageable, filter);
	}
	private String getParams(Pageable pageable, WineFilter filter){
		String page = ParamBuilder.getParams(pageable);
		StringBuilder buffer = new StringBuilder(page);
		if(!filter.getMax().isEmpty()){
			buffer.append("&max=");
			buffer.append(filter.getMax());
		}
		if(!filter.getMin().isEmpty()){
			buffer.append("&min=");
			buffer.append(filter.getMin());
		}
		if(!filter.getMakerIds().isEmpty()){
			for (Integer id : filter.getMakerIds()) {
				buffer.append("&makerIds=");
				buffer.append(id);
			}
		}
		if(!filter.getTypeIds().isEmpty()){
			for (Integer id : filter.getTypeIds()) {
				buffer.append("&typeIds=");
				buffer.append(id);
			}
		}
		if(!filter.getCountryIds().isEmpty()){
			for (Integer id : filter.getCountryIds()) {
				buffer.append("&countryIds=");
				buffer.append(id);
			}
		}
		if(!filter.getCapacityIds().isEmpty()){
			for (Integer id : filter.getCapacityIds()) {
				buffer.append("&capacityIds=");
				buffer.append(id);
			}
		}
		if(!filter.getYearIds().isEmpty()){
			for (Integer id : filter.getYearIds()) {
				buffer.append("&yearIds=");
				buffer.append(id);
			}
		}
		if(!filter.getAlcoholContentIds().isEmpty()){
			for (Integer id : filter.getAlcoholContentIds()) {
				buffer.append("&alcoholContentIds=");
				buffer.append(id);
			}
		}
		if (!filter.getSearch().isEmpty()) {
			buffer.append("&search=");
			buffer.append(filter.getSearch());
		}
		return buffer.toString();
	}
}
