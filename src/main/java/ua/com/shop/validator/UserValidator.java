package ua.com.shop.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.shop.entity.User;
import ua.com.shop.service.UserService;

public class UserValidator implements Validator {
	
	private final static Pattern REG1 = Pattern
			.compile("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}");
	private final static Pattern REG2 = Pattern.compile("([0-9]{10})|()");
	private final static Pattern REG3 = Pattern.compile("([a-zA-Z0-9]{0,20})");
	private final static Pattern REG4 = Pattern.compile("([a-zA-Z]{0,20})");

	private final UserService userService;

	public UserValidator(UserService userService) {
		this.userService = userService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "",
				"Can't be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "",
				"Can't be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "",
				"Can't be empty");
		if (!REG1.matcher(user.getEmail()).matches()) {
			errors.rejectValue("email", "", "Incorrect input");
		}
		if (userService.findByEmails(user.getEmail()) != null) {
			errors.rejectValue("email", "", "Email already exist");
		}
	}

}