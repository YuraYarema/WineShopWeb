package ua.com.shop.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.shop.dto.form.AlcoholContentForm;
import ua.com.shop.service.AlcoholContentService;



public class AlcoholContentValidator implements  Validator{
	
	private final static Pattern REG = Pattern
			.compile("([0-9]{1,2}\\.[0-9]{1,2})|([0-9]{1,2})");

	private final AlcoholContentService alcoholContentService;
	
	
	public AlcoholContentValidator(AlcoholContentService alcoholContentService) {
		this.alcoholContentService = alcoholContentService;
	}

	public boolean supports(Class<?> clazz) {
		return AlcoholContentForm.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		AlcoholContentForm form = (AlcoholContentForm) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "alcoholContent", "",
				"Can't be empty");
		if (!REG.matcher(String.valueOf(form.getAlcoholContent())).matches()) {
			errors.rejectValue("alcoholContent", "",
					"Enter numbers ");
		}

		if (errors.getFieldError("alcoholContent") == null) {
			if (alcoholContentService.findUnique(form.getAlcoholContent()) != null) {
				errors.rejectValue("alcoholContent", "", "Already exist!");
			}
		}
		
	}

}
