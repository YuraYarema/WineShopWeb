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

import ua.com.shop.dto.filter.CapacityFilter;
import ua.com.shop.dto.filter.YearFilter;
import ua.com.shop.dto.form.YearForm;
import ua.com.shop.service.YearService;
import ua.com.shop.util.ParamBuilder;
import ua.com.shop.validator.YearValidator;

@Controller
@RequestMapping("/admin/year")
@SessionAttributes("year")
public class YearController {

	@Autowired
	private YearService yearService;
	
	@InitBinder("year")
	protected void bind(WebDataBinder binder) {
		binder.setValidator(new YearValidator(yearService));
	}
	@ModelAttribute("filter")
	public YearFilter getFilter() {
		return new YearFilter();
	}
	
	@ModelAttribute("year")
	public YearForm getForm(){
		return new YearForm();
	}
	@GetMapping
	public String show(Model model, @PageableDefault Pageable pageable,
			@ModelAttribute("filter") YearFilter filter){
		model.addAttribute("page", yearService.findAll(pageable, filter));
		return "admin-year";
	}
	@GetMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, @PageableDefault Pageable pageable,
			@ModelAttribute("filter") YearFilter filter){
		model.addAttribute("year", yearService.findForm(id));
		return show(model, pageable, filter);
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id, @PageableDefault Pageable pageable,
			@ModelAttribute("filter") YearFilter filter){
		yearService.delete(id);
		return "redirect:/admin/year"+getParams(pageable, filter);
	}
	
	@PostMapping
	public String save(@ModelAttribute("year")@Valid YearForm yearForm,BindingResult br, Model model, SessionStatus status, @PageableDefault Pageable pageable,
			@ModelAttribute("filter") YearFilter filter){
		if (br.hasErrors()){
			return show(model, pageable, filter);
		}
		yearService.save(yearForm);
		status.setComplete();
		return "redirect:/admin/year"+getParams(pageable, filter);
	}
	private String getParams(Pageable pageable, YearFilter filter){
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
		return buffer.toString();
	}
}
