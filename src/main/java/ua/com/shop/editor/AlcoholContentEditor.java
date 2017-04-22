package ua.com.shop.editor;

import java.beans.PropertyEditorSupport;

import ua.com.shop.dto.form.AlcoholContentForm;
import ua.com.shop.entity.AlcoholContent;
import ua.com.shop.entity.Country;
import ua.com.shop.service.AlcoholContentService;

public class AlcoholContentEditor extends PropertyEditorSupport{

	private final AlcoholContentService alcoholContentService;

	public AlcoholContentEditor(AlcoholContentService alcoholContentService) {
		this.alcoholContentService = alcoholContentService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		AlcoholContent alcoholContent = alcoholContentService.findOne(Integer.valueOf(text));
		setValue(alcoholContent);
	}

	
	
}
