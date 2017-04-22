package ua.com.shop.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.shop.dto.form.YearForm;
import ua.com.shop.service.YearService;

public class YearValidator implements Validator {

	private final static Pattern REG = Pattern
			.compile("^([0-9]{1,17}\\.[0-9]{1,2})|([0-9]{1,17}\\,[0-9]{1,2})|([0-9]{1,17})$");

	private final YearService yearService;

	public YearValidator(YearService yearService) {
		this.yearService = yearService;
	}

	public boolean supports(Class<?> clazz) {
		return YearForm.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		YearForm form = (YearForm) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "year", "",
				"Can't be empty");

		if (!REG.matcher(String.valueOf(form.getYear())).matches()) {
			errors.rejectValue("year", "", "Enter numbers ");
		}

		if (errors.getFieldError("year") == null) {
			if (yearService.findUnique(form.getYear()) != null) {
				errors.rejectValue("year", "", "Already exist!");
			}
		}
	}

}
