package ua.com.shop.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.com.shop.dao.AlcoholContentDao;
import ua.com.shop.dto.filter.AlcoholContentFilter;
import ua.com.shop.dto.form.AlcoholContentForm;
import ua.com.shop.entity.AlcoholContent;
import ua.com.shop.service.AlcoholContentService;
import ua.com.shop.specification.AlcoholContentSpecification;
import ua.com.shop.specification.CapacitySpecification;

@Service
public class AlcoholContentServiceImpl implements AlcoholContentService{
	
	@Autowired
	private AlcoholContentDao alcoholContentDao;

	public List<AlcoholContent> findAll() {
		return alcoholContentDao.findAll();

	}

	public AlcoholContent findOne(int id) {
		return alcoholContentDao.findOne(id);
	}

	public void delete(int id) {
		alcoholContentDao.delete(id);
		
	}

	public void save(AlcoholContent alcoholContent) {
		alcoholContentDao.save(alcoholContent);
		
	}

	public AlcoholContent findUnique(String alcoholContent) {
		return alcoholContentDao.findUnique(Double.valueOf(alcoholContent));
	}

	public AlcoholContentForm findForm(int id) {
		AlcoholContentForm form = new AlcoholContentForm();
		AlcoholContent entity = alcoholContentDao.findOne(id);
		form.setId(entity.getId());
		form.setAlcoholContent(String.valueOf(entity.getAlcoholContent()));
		return form;
	}

	public void save(AlcoholContentForm form) {
		AlcoholContent entity = new AlcoholContent();
		entity.setId(form.getId());
		entity.setAlcoholContent(Double.valueOf(form.getAlcoholContent()));
		alcoholContentDao.save(entity);
	}

	@Override
	public Page<AlcoholContent> findAll(Pageable pageable,
			AlcoholContentFilter filter) {
		return alcoholContentDao.findAll(new AlcoholContentSpecification(filter), pageable);
	}
}
