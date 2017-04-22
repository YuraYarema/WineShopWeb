package ua.com.shop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.shop.dto.filter.CapacityFilter;
import ua.com.shop.dto.filter.SimpleFilter;
import ua.com.shop.dto.form.CapacityForm;
import ua.com.shop.entity.Capacity;

public interface CapacityService {

	List<Capacity> findAll();
	
	Capacity findOne(int id);
	
	void delete(int id);

	void save(Capacity capacity);

	Capacity findUnique(String capacity);

	CapacityForm findForm(int id);

	void save(CapacityForm capacityForm);

	Page<Capacity> findAll(Pageable pageable, CapacityFilter filter);

}
