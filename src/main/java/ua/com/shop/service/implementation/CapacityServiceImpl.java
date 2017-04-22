package ua.com.shop.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.com.shop.dao.CapacityDao;
import ua.com.shop.dto.filter.CapacityFilter;
import ua.com.shop.dto.form.CapacityForm;
import ua.com.shop.entity.Capacity;
import ua.com.shop.service.CapacityService;
import ua.com.shop.specification.CapacitySpecification;

@Service
public class CapacityServiceImpl implements CapacityService{

	@Autowired
	private CapacityDao capacityDao;
	
	public List<Capacity> findAll() {
		return capacityDao.findAll();
	}

	public Capacity findOne(int id) {
		return capacityDao.findOne(id);
	}

	public void delete(int id) {
		capacityDao.delete(id);		
	}

	public void save(Capacity capacity) {
		capacityDao.save(capacity);		
	}

	public Capacity findUnique(String capacity) {
		return capacityDao.findUnique(Double.valueOf(capacity));
	}

	public CapacityForm findForm(int id) {
		CapacityForm form = new CapacityForm();
		Capacity entity = capacityDao.findOne(id);
		form.setId(entity.getId());
		form.setCapacity(String.valueOf(entity.getCapacity()));
		return form;
	}

	public void save(CapacityForm form) {
		Capacity entity = new Capacity();
		entity.setId(form.getId());
		entity.setCapacity(Double.valueOf(form.getCapacity()));
		capacityDao.save(entity);
	}

	public Page<Capacity> findAll(Pageable pageable, CapacityFilter filter) {
		return capacityDao.findAll(new CapacitySpecification(filter), pageable);
	}

}
