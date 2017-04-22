package ua.com.shop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.shop.dto.filter.AlcoholContentFilter;
import ua.com.shop.dto.form.AlcoholContentForm;
import ua.com.shop.entity.AlcoholContent;

public interface AlcoholContentService {

	List<AlcoholContent> findAll();
	
	AlcoholContent findOne(int id);
	
	void delete(int id);

	AlcoholContent findUnique(String alcoholContent);

	AlcoholContentForm findForm(int id);


	void save(AlcoholContentForm alcoholContentForm);

	Page<AlcoholContent> findAll(Pageable pageable, AlcoholContentFilter filter);
	
}
