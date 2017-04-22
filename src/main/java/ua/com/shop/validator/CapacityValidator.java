package ua.com.shop.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.shop.dto.form.AlcoholContentForm;
import ua.com.shop.dto.form.CapacityForm;
import ua.com.shop.service.CapacityService;

public class CapacityValidator implements Validator {

	private final static Pattern REG = Pattern
			.compile("([0-9]{1,2}\\.[0-9]{1,2})|([0-9]{1,2})");

	private final CapacityService capacityService;

	public CapacityValidator(CapacityService capacityService) {
		this.capacityService = capacityService;
	}

	public boolean supports(Class<?> clazz) {
		return CapacityForm.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		CapacityForm form = (CapacityForm) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "capacity", "",
				"Can't be empty");
		
		if (!REG.matcher(String.valueOf(form.getCapacity())).matches()) {
			errors.rejectValue("capacity", "", "Enter numbers ");
		}

		if (errors.getFieldError("capacity") == null) {
			if (capacityService.findUnique(form.getCapacity()) != null) {
				errors.rejectValue("capacity", "", "Already exist!");
			}
		}

	}

}
