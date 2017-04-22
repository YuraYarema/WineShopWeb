package ua.com.shop.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.shop.entity.Type;
import ua.com.shop.service.TypeService;

public class TypeValidator implements Validator{

	private final TypeService typeService;
	


	public TypeValidator(TypeService typeService) {
		this.typeService = typeService;
	}

	public boolean supports(Class<?> clazz) {
		return Type.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		Type type = (Type) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "type", "",
				"Can't be empty");
		if(typeService.findByType(type.getType())!=null){
			errors.rejectValue("type", "", "Already exist");
		}
		
	}

}
