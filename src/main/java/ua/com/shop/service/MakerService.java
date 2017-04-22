package ua.com.shop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.shop.dto.filter.SimpleFilter;
import ua.com.shop.entity.Country;
import ua.com.shop.entity.Maker;
import ua.com.shop.entity.Type;

public interface MakerService {

	List<Maker> findAll();
	
	Maker findOne(int id);
	
	void delete(int id);

	void save(Maker maker);

	Maker findUnique(String nameCompany);

	Page<Maker> findAll(Pageable pageable, SimpleFilter filter);

}
