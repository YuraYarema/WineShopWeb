package ua.com.shop.editor;

import java.beans.PropertyEditorSupport;

import ua.com.shop.entity.Country;
import ua.com.shop.entity.Type;
import ua.com.shop.service.TypeService;

public class TypeEditor extends PropertyEditorSupport{

	private final TypeService typeService;

	public TypeEditor(TypeService typeService) {
		this.typeService = typeService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Type type = typeService.findOne(Integer.valueOf(text));
		setValue(type);
		
	}
	
	
}
