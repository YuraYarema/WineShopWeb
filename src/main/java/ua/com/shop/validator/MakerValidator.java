package ua.com.shop.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.shop.entity.Maker;
import ua.com.shop.service.MakerService;

public class MakerValidator implements Validator{

	private final MakerService makerService;
	
	
	public MakerValidator(MakerService makerService) {
		this.makerService = makerService;
	}

	public boolean supports(Class<?> clazz) {
		return Maker.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		Maker maker= (Maker) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nameCompany", "", "Cant be empty");
		
		if(errors.getFieldError("nameCompany")==null) {
			if(makerService.findUnique(maker.getNameCompany())!=null){
				errors.rejectValue("nameCompany", "", "Already exist");
			}
		}
	}

}
