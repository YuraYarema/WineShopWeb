package ua.com.shop.editor;

import java.beans.PropertyEditorSupport;

import ua.com.shop.entity.Country;
import ua.com.shop.entity.Maker;
import ua.com.shop.service.MakerService;

public class MakerEditor extends PropertyEditorSupport{

	private final MakerService makerService;

	public MakerEditor(MakerService makerService) {
		this.makerService = makerService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Maker maker = makerService.findOne(Integer.valueOf(text));
		setValue(maker);
	}
	
	
}
