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
import ua.com.shop.dto.form.CapacityForm;
import ua.com.shop.service.CapacityService;
import ua.com.shop.util.ParamBuilder;
import ua.com.shop.validator.CapacityValidator;

@Controller
@RequestMapping("/admin/capacity")
@SessionAttributes("capacity")
public class CapacityController {

	@Autowired
	private CapacityService capacityService;

	@InitBinder("capacity")
	protected void bind(WebDataBinder binder) {
		binder.setValidator(new CapacityValidator(capacityService));
	}

	@ModelAttribute("filter")
	public CapacityFilter getFilter() {
		return new CapacityFilter();
	}

	@ModelAttribute("capacity")
	public CapacityForm getForm() {
		return new CapacityForm();
	}

	@GetMapping
	public String show(Model model, @PageableDefault Pageable pageable,
			@ModelAttribute("filter") CapacityFilter filter) {
		model.addAttribute("page", capacityService.findAll(pageable, filter));
		return "admin-capacity";
	}

	@GetMapping("/update/{id}")
	public String update(@PathVariable int id, Model model,
			@PageableDefault Pageable pageable,
			@ModelAttribute("filter") CapacityFilter filter) {
		model.addAttribute("capacity", capacityService.findForm(id));
		return show(model, pageable, filter);
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id,
			@PageableDefault Pageable pageable,
			@ModelAttribute("filter") CapacityFilter filter) {
		capacityService.delete(id);
		return "redirect:/admin/capacity"+getParams(pageable, filter);
	}

	@PostMapping
	public String save(
			@ModelAttribute("capacity") @Valid CapacityForm capacityForm,
			BindingResult br, Model model, SessionStatus status,
			@PageableDefault Pageable pageable,
			@ModelAttribute("filter") CapacityFilter filter) {
		if (br.hasErrors()) {
			return show(model, pageable, filter);
		}
		capacityService.save(capacityForm);
		status.setComplete();
		return "redirect:/admin/capacity"+getParams(pageable, filter);
	}
	
	private String getParams(Pageable pageable, CapacityFilter filter){
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
