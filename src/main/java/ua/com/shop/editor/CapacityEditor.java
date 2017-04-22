package ua.com.shop.editor;

import java.beans.PropertyEditorSupport;

import ua.com.shop.entity.Capacity;
import ua.com.shop.entity.Country;
import ua.com.shop.service.CapacityService;

public class CapacityEditor extends PropertyEditorSupport{

	private final CapacityService capacityService;

	public CapacityEditor(CapacityService capacityService) {
		this.capacityService = capacityService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Capacity capacity = capacityService.findOne(Integer.valueOf(text));
		setValue(capacity);
	}
	
	
}
