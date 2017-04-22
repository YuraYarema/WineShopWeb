package ua.com.shop.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.com.shop.dao.CountryDao;
import ua.com.shop.dto.filter.SimpleFilter;
import ua.com.shop.entity.Country;
import ua.com.shop.service.CountryService;
import ua.com.shop.specification.CountrySpecification;
import ua.com.shop.specification.TypeSpecification;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryDao countryDao;

	public List<Country> findAll() {
		return countryDao.findAll();
	}

	public Country findOne(int id) {
		return countryDao.findOne(id);
	}

	public void delete(int id) {
		countryDao.delete(id);
	}

	public void save(Country country) {
		countryDao.save(country);	
	}

	public Country findByCountry(String country) {
		return countryDao.findByCountry(country);
	}

	@Override
	public Page<Country> findAll(Pageable pageable, SimpleFilter filter) {
		return countryDao.findAll(new CountrySpecification(filter), pageable);
	}

}
