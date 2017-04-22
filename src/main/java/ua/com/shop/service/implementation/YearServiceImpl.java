package ua.com.shop.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.com.shop.dao.YearDao;
import ua.com.shop.dto.filter.YearFilter;
import ua.com.shop.dto.form.YearForm;
import ua.com.shop.entity.Year;
import ua.com.shop.service.YearService;
import ua.com.shop.specification.CapacitySpecification;
import ua.com.shop.specification.YearSpecification;

@Service
public class YearServiceImpl implements YearService {

	@Autowired
	private YearDao yearDao;

	public List<Year> findAll() {
		return yearDao.findAll();
	}

	public Year findOne(int id) {
		return yearDao.findOne(id);
	}

	public void delete(int id) {
		yearDao.delete(id);
	}

	public void save(Year year) {
		yearDao.save(year);
	}

	public Year findUnique(String year) {
		return yearDao.findUnique(Integer.valueOf(year));
	}

	public YearForm findForm(int id) {
		YearForm form = new YearForm();
		Year entity = yearDao.findOne(id);
		form.setId(entity.getId());
		form.setYear(String.valueOf(entity.getYear()));
		return form;
	}

	public void save(YearForm form) {
		Year entity = new Year();
		entity.setId(form.getId());
		entity.setYear(Integer.valueOf(form.getYear()));
		yearDao.save(entity);
	}

	@Override
	public Page<Year> findAll(Pageable pageable, YearFilter filter) {
		return yearDao.findAll(new YearSpecification(filter), pageable);
	}
}
