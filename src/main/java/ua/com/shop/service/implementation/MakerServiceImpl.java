package ua.com.shop.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.com.shop.dao.MakerDao;
import ua.com.shop.dto.filter.SimpleFilter;
import ua.com.shop.entity.Country;
import ua.com.shop.entity.Maker;
import ua.com.shop.entity.Type;
import ua.com.shop.service.MakerService;
import ua.com.shop.specification.MakerSpecification;
import ua.com.shop.specification.TypeSpecification;

@Service
public class MakerServiceImpl implements MakerService{
	
	@Autowired
	private MakerDao makerDao;

	public List<Maker> findAll() {
		return makerDao.findAll();
	}

	public Maker findOne(int id) {
		return makerDao.findOne(id);
	}

	public void delete(int id) {
		makerDao.delete(id);		
	}

	public void save(Maker maker) {
		makerDao.save(maker);		
	}

	public Maker findUnique(String nameCompany) {
		return makerDao.findUnique(nameCompany);
	}


	@Override
	public Page<Maker> findAll(Pageable pageable, SimpleFilter filter) {
		return makerDao.findAll(new MakerSpecification(filter), pageable);
	}

}
