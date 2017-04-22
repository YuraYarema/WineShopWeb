package ua.com.shop.editor;

import java.beans.PropertyEditorSupport;

import ua.com.shop.entity.Country;
import ua.com.shop.service.CountryService;

public class CountryEditor extends PropertyEditorSupport{

	private final CountryService countryService;

	public CountryEditor(CountryService countryService) {
		this.countryService = countryService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Country country = countryService.findOne(Integer.valueOf(text));
		setValue(country);
	}
	
	
}
