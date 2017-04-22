package ua.com.shop.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.shop.dto.form.WineForm;
import ua.com.shop.service.WineService;

public class WineValidator implements Validator {
	
	private final static Pattern REG = Pattern.compile("^([0-9]{1,17}\\.[0-9]{1,2})|([0-9]{1,17}\\,[0-9]{1,2})|([0-9]{1,17})$");


	private final WineService wineService;

	public WineValidator(WineService wineService) {
		this.wineService = wineService;
	}

	public boolean supports(Class<?> clazz) {
		return WineForm.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		WineForm form = (WineForm) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "",
				"Cant be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "",
				"Cant be empty");
		
		if(!REG.matcher(String.valueOf(form.getPrice())).matches()){
			errors.rejectValue("price", "", "Enter number");
		}

		if (errors.getFieldError("name")==null && errors.getFieldError("price")==null) {
			if (wineService.findUnique(form.getName(),form.getPrice(),form.getType(),form.getYear(),
					form.getMaker(),form.getCapacity(),form.getAlcoholContent(),form.getCountry()) != null) {
				errors.rejectValue("name", "", "Already exist");
			}
		}

	}

}
