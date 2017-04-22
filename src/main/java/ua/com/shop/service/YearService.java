package ua.com.shop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.shop.dto.filter.YearFilter;
import ua.com.shop.dto.form.YearForm;
import ua.com.shop.entity.Year;

public interface YearService {

	List<Year> findAll();
	
	Year findOne(int id);
	
	void delete(int id);

	void save(Year year);

	Year findUnique(String year);

	YearForm findForm(int id);

	void save(YearForm yearForm);

	Page<Year> findAll(Pageable pageable, YearFilter filter);
}
