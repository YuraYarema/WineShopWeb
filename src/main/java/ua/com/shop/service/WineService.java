package ua.com.shop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.shop.dto.filter.WineFilter;
import ua.com.shop.dto.form.WineForm;
import ua.com.shop.entity.AlcoholContent;
import ua.com.shop.entity.Capacity;
import ua.com.shop.entity.Country;
import ua.com.shop.entity.Maker;
import ua.com.shop.entity.Type;
import ua.com.shop.entity.Wine;
import ua.com.shop.entity.Year;

public interface WineService {

	List<Wine> findAll();
	
	Wine findOne(Integer id);
	
	void delete(int id);

	Wine findUnique(String name, String price, Type type, Year year,
			Maker maker, Capacity capacity, AlcoholContent alcoholContent, Country country);

	WineForm findForm(int id);

	void save(WineForm form);

	List<Wine> findByWineId(int id);

	List<Wine> findByCountryId(int id);

	List<Wine> findByTypeId(int id);

	List<Wine> findByCategoriId(int id);

	Page<Wine> findAll(Pageable pageable, WineFilter filter);

}
