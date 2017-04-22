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
import ua.com.shop.editor.CountryEditor;
import ua.com.shop.entity.Country;
import ua.com.shop.entity.Maker;
import ua.com.shop.service.CountryService;
import ua.com.shop.service.MakerService;
import ua.com.shop.util.ParamBuilder;
import ua.com.shop.validator.MakerValidator;
import static ua.com.shop.util.ParamBuilder.*;

@Controller
@RequestMapping("/admin/maker")
@SessionAttributes("maker")
public class MakerController {

	@Autowired
	private MakerService makerService;

	@Autowired
	private CountryService countryService;

	@InitBinder("maker")
	protected void bind(WebDataBinder binder) {
		binder.setValidator(new MakerValidator(makerService));
	}

	@ModelAttribute("filter")
	public SimpleFilter  getFilter() {
		return new SimpleFilter ();
	}

	@ModelAttribute("maker")
	public Maker getForm() {
		return new Maker();
	}

	@RequestMapping
	public String show(Model model, @PageableDefault Pageable pageable,
			@ModelAttribute("filter") SimpleFilter  filter) {
		model.addAttribute("page", makerService.findAll(pageable, filter));
		model.addAttribute("countrys", countryService.findAll());
		return "admin-maker";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id,
			@PageableDefault Pageable pageable,
			@ModelAttribute("filter") SimpleFilter  filter) {
		makerService.delete(id);
		return "redirect:/admin/maker"+getParams(pageable, filter);
	}

	@GetMapping("/update/{id}")
	public String update(@PathVariable int id, Model model,
			@PageableDefault Pageable pageable,
			@ModelAttribute("filter") SimpleFilter  filter) {
		model.addAttribute("maker", makerService.findOne(id));
		return show(model, pageable, filter);
	}

	@PostMapping
	public String save(@ModelAttribute("maker") @Valid Maker maker,
			BindingResult br, Model model, SessionStatus status,
			@PageableDefault Pageable pageable,
			@ModelAttribute("filter") SimpleFilter  filter) {
		if (br.hasErrors()) {
			return show(model, pageable, filter);
		}
		makerService.save(maker);
		status.setComplete();
		return "redirect:/admin/maker"+getParams(pageable, filter);
	}
}
