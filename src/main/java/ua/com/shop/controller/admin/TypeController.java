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

import ua.com.shop.dto.filter.SimpleFilter;
import ua.com.shop.entity.Type;
import ua.com.shop.service.TypeService;
import ua.com.shop.validator.TypeValidator;
import static ua.com.shop.util.ParamBuilder.*;

@Controller
@RequestMapping("/admin/type")
@SessionAttributes("type")
public class TypeController {

	@Autowired
	private TypeService typeService;

	@InitBinder("type")
	protected void bind(WebDataBinder binder) {
		binder.setValidator(new TypeValidator(typeService));
	}

	@ModelAttribute("type")
	public Type getForm() {
		return new Type();
	}

	@ModelAttribute("filter")
	public SimpleFilter getFilter() {
		return new SimpleFilter();
	}

	@GetMapping
	public String show(Model model, @PageableDefault Pageable pageable,
			@ModelAttribute("filter") SimpleFilter filter) {
		model.addAttribute("page", typeService.findAll(pageable, filter));
		return "admin-type";
	}

	@GetMapping("/update/{id}")
	public String update(@PathVariable int id, Model model,
			@PageableDefault Pageable pageable,
			@ModelAttribute("filter") SimpleFilter filter) {
		model.addAttribute("type", typeService.findOne(id));
		show(model, pageable, filter);
		return "admin-type";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id,
			@PageableDefault Pageable pageable,
			@ModelAttribute("filter") SimpleFilter filter) {
		typeService.delete(id);
		return "redirect:/admin/type"+getParams(pageable, filter);
	}

	@PostMapping
	public String save(@ModelAttribute("type") @Valid Type type,
			BindingResult br, Model model, SessionStatus status,
			@PageableDefault Pageable pageable,
			@ModelAttribute("filter") SimpleFilter filter) {
		if (br.hasErrors()) {
			return show(model, pageable, filter);
		}
		typeService.save(type);
		status.setComplete();
		return "redirect:/admin/type"+getParams(pageable, filter);
	}

}
