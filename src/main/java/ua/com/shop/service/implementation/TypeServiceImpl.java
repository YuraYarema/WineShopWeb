package ua.com.shop.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.com.shop.dao.TypeDao;
import ua.com.shop.dto.filter.SimpleFilter;
import ua.com.shop.entity.Type;
import ua.com.shop.service.TypeService;
import ua.com.shop.specification.TypeSpecification;

@Service
public class TypeServiceImpl implements TypeService{
	
	@Autowired
	private TypeDao typeDao;

	public List<Type> findAll() {
		return typeDao.findAll();
	}

	public Type findOne(int id) {
		return typeDao.findOne(id);
	}

	public void delete(int id) {
		typeDao.delete(id);			
	}

	public void save(Type type) {
		typeDao.save(type);				
	}

	public Type findByType(String type) {
		return typeDao.findByName(type);
	}

	@Override
	public Page<Type> findAll(Pageable pageable, SimpleFilter filter) {
		return typeDao.findAll(new TypeSpecification(filter), pageable);
	}

}
