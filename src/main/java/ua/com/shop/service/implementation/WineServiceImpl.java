package ua.com.shop.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ua.com.shop.dao.AlcoholContentDao;
import ua.com.shop.dao.CapacityDao;
import ua.com.shop.dao.MakerDao;
import ua.com.shop.dao.TypeDao;
import ua.com.shop.dao.WineDao;
import ua.com.shop.dao.YearDao;
import ua.com.shop.dto.filter.WineFilter;
import ua.com.shop.dto.form.WineForm;
import ua.com.shop.entity.AlcoholContent;
import ua.com.shop.entity.Capacity;
import ua.com.shop.entity.Country;
import ua.com.shop.entity.Maker;
import ua.com.shop.entity.Type;
import ua.com.shop.entity.Wine;
import ua.com.shop.entity.Year;
import ua.com.shop.service.AlcoholContentService;
import ua.com.shop.service.CapacityService;
import ua.com.shop.service.FileWriter;
import ua.com.shop.service.FileWriter.Folder;
import ua.com.shop.service.MakerService;
import ua.com.shop.service.TypeService;
import ua.com.shop.service.WineService;
import ua.com.shop.service.YearService;
import ua.com.shop.specification.WineSpecification;

@Service
public class WineServiceImpl implements WineService{

	@Autowired
	private WineDao wineDao;

	@Autowired
	private MakerDao makerDao;
	
	@Autowired
	private TypeDao typeDao;
	
	@Autowired
	private CapacityDao capacityDao;
	
	@Autowired
	private YearDao yearDao;
	
	@Autowired
	private AlcoholContentDao alcoholContentDao;
	
	@Autowired
	private FileWriter fileWriter;
	
	public List<Wine> findAll() {
		return wineDao.findAll();
	}

	public Wine findOne(Integer id) {
		return wineDao.findOne(id);
	}

	public void delete(int id) {
		wineDao.delete(id);
		
	}

	public Wine findUnique(String name, String price, Type type, Year year,
			Maker maker, Capacity capacity, AlcoholContent alcoholContent, Country country) {
		return wineDao.findUnique(name,Double.valueOf(price),type.getId(),year.getId()
				,maker.getId(),capacity.getId(),alcoholContent.getId(),country.getId());
	}

	public WineForm findForm(int id) {
		WineForm form = new WineForm();
		Wine entity = wineDao.findOne(id);
		form.setId(entity.getId());
		form.setName(entity.getName());
		form.setPrice(String.valueOf(entity.getPrice()));
		form.setType(entity.getType());
		form.setYear(entity.getYear());
		form.setMaker(entity.getMaker());
		form.setCapacity(entity.getCapacity());
		form.setAlcoholContent(entity.getAlcoholContent());
		form.setCountry(entity.getCountry());
		form.setFile(entity.getFile());
		form.setVersion(entity.getVersion());
		return form;
	}

	public void save(WineForm form) {
		Wine entity = new Wine();
		entity.setId(form.getId());
		entity.setName(form.getName());
		entity.setPrice(Double.valueOf(form.getPrice()));
		entity.setType(form.getType());
		entity.setYear(form.getYear());
		entity.setMaker(form.getMaker());
		entity.setCapacity(form.getCapacity());
		entity.setAlcoholContent(form.getAlcoholContent());
		entity.setCountry(form.getCountry());
		entity.setFile(form.getFile());
		entity.setVersion(form.getVersion());
		MultipartFile file = entity.getFile();
		entity = wineDao.saveAndFlush(entity);
		if(fileWriter.write(Folder.WINE, file, entity.getId())){
			entity.setVersion(entity.getVersion()+1);
			wineDao.save(entity);
		}
		wineDao.save(entity);
		
	}

	public List<Wine> findByWineId(int id) {
		return wineDao.findByWineId(id);
	}

	public List<Wine> findByCountryId(int id) {
		return wineDao.findByCountryId(id);
	}

	public List<Wine> findByTypeId(int id) {
		return wineDao.findByTypeId(id);
	}

	public List<Wine> findByCategoriId(int id) {
		return wineDao.findByCategoriId(id);
	}

	@Override
	public Page<Wine> findAll(Pageable pageable, WineFilter filter) {
		return wineDao.findAll(new WineSpecification(filter), pageable);
	}



}
