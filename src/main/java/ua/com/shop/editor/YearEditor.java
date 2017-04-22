package ua.com.shop.editor;

import java.beans.PropertyEditorSupport;

import ua.com.shop.entity.Country;
import ua.com.shop.entity.Year;
import ua.com.shop.service.YearService;

public class YearEditor extends PropertyEditorSupport{

	private final YearService yearService;



	public YearEditor(YearService yearService) {
		this.yearService = yearService;
	}



	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Year year = yearService.findOne(Integer.valueOf(text));
		setValue(year);
	}
	
	
}
