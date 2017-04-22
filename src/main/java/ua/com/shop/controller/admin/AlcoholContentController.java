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

import ua.com.shop.dto.filter.AlcoholContentFilter;
import ua.com.shop.dto.filter.CapacityFilter;
import ua.com.shop.dto.form.AlcoholContentForm;
import ua.com.shop.entity.AlcoholContent;
import ua.com.shop.service.AlcoholContentService;
import ua.com.shop.util.ParamBuilder;
import ua.com.shop.validator.AlcoholContentValidator;

@Controller
@RequestMapping(value="/admin/alcoholContent")
@SessionAttributes(value="alcoholContent")
public class AlcoholContentController {

	@Autowired
	private AlcoholContentService alcoholContentService;

	@InitBinder(value="alcoholContent")
	protected void bind(WebDataBinder binder) {
		binder.setValidator(new AlcoholContentValidator(alcoholContentService));
	}

	@ModelAttribute(value="filter")
	public AlcoholContentFilter getFilter() {
		return new AlcoholContentFilter();
	}

	@ModelAttribute(value="alcoholContent")
	public AlcoholContentForm getForm() {
		return new AlcoholContentForm();
	}

	@GetMapping
	public String show(Model model, @PageableDefault Pageable pageable,
			@ModelAttribute(value="filter") AlcoholContentFilter filter) {
		model.addAttribute("page",
				alcoholContentService.findAll(pageable, filter));
		return "admin-alcoholContent";
	}

	@GetMapping(value="/update/{id}")
	public String update(@PathVariable int id, Model model,
			@PageableDefault Pageable pageable,
			@ModelAttribute(value="filter") AlcoholContentFilter filter) {
		model.addAttribute("alcoholContent", alcoholContentService.findForm(id));
		return show(model, pageable, filter);
	}

	@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable int id,
			@PageableDefault Pageable pageable,
			@ModelAttribute(value="filter") AlcoholContentFilter filter) {
		alcoholContentService.delete(id);
		return "redirect:/admin/alcoholContent"+getParams(pageable, filter);
	}

	@PostMapping
	public String save(
			@ModelAttribute(value="alcoholContent") @Valid AlcoholContentForm alcoholContentForm,
			BindingResult br, Model model, SessionStatus status,
			@PageableDefault Pageable pageable,
			@ModelAttribute(value="filter") AlcoholContentFilter filter) {
		if (br.hasErrors()) {
			return show(model, pageable, filter);
		}
		alcoholContentService.save(alcoholContentForm);
		status.setComplete();
		return "redirect:/admin/alcoholContent"+getParams(pageable, filter);
	}
	
	private String getParams(Pageable pageable, AlcoholContentFilter filter){
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
