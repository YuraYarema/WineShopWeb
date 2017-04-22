package ua.com.shop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.shop.dto.filter.SimpleFilter;
import ua.com.shop.entity.Type;

public interface TypeService {

	List<Type> findAll();
	
	Type findOne(int id);
	
	void delete(int id);

	void save(Type type);

	Type findByType(String type);

	Page<Type> findAll(Pageable pageable, SimpleFilter filter);
}
