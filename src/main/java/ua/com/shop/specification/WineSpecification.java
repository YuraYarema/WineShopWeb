package ua.com.shop.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.com.shop.dto.filter.SimpleFilter;
import ua.com.shop.dto.filter.WineFilter;
import ua.com.shop.entity.Wine;

public class WineSpecification implements Specification<Wine> {

	private final WineFilter filter;
	
	private final List<Predicate> predicates = new ArrayList<>();

	private final static Pattern PATTERN = Pattern
			.compile("^([0-9]{1,17}\\.[0-9]{1,2})|([0-9]{1,17}\\,[0-9]{1,2})|([0-9]{1,17})$");

	public WineSpecification(WineFilter filter) {
		this.filter = filter;
		if(PATTERN.matcher(filter.getMax()).matches()){
			filter.setMaxValue(Double.valueOf(filter.getMax().replace(',', '.')));
		}
		if(PATTERN.matcher(filter.getMin()).matches()){
			filter.setMinValue(Double.valueOf(filter.getMin().replace(',', '.')));
		}
	}
	private void filterByPrice(Root<Wine> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(filter.getMaxValue()!=null){
			predicates.add(cb.le(root.get("price"), filter.getMaxValue()));
		}
		if(filter.getMinValue()!=null){
			predicates.add(cb.ge(root.get("price"), filter.getMinValue()));
		}
	}
	private void filterByMaker(Root<Wine> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getMakerIds().isEmpty()){
			predicates.add(root.get("maker").in(filter.getMakerIds()));
		}
	}
	private void filterByCountry(Root<Wine> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getCountryIds().isEmpty()){
			predicates.add(root.get("country").in(filter.getCountryIds()));
		}
	}
	private void filterByType(Root<Wine> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getTypeIds().isEmpty()){
			predicates.add(root.get("type").in(filter.getTypeIds()));
		}
	}
	private void filterByCapacity(Root<Wine> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getCapacityIds().isEmpty()){
			predicates.add(root.get("capacity").in(filter.getCapacityIds()));
		}
	}
	private void filterByYear(Root<Wine> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getYearIds().isEmpty()){
			predicates.add(root.get("year").in(filter.getYearIds()));
		}
	}
	private void filterByAlcoholContent(Root<Wine> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getAlcoholContentIds().isEmpty()){
			predicates.add(root.get("alcoholContent").in(filter.getAlcoholContentIds()));
		}
	}
	private void fetch(Root<Wine> root, CriteriaQuery<?> query){
		if(!query.getResultType().equals(Long.class)){
			query.distinct(true);
			root.fetch("maker");
			root.fetch("type");
			root.fetch("capacity");
			root.fetch("year");
			root.fetch("alcoholContent");
			root.fetch("country");
		}
	}
	private void filterByName(Root<Wine> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getSearch().isEmpty()){
			predicates.add(cb.like(cb.lower(root.get("name")), filter.getSearch().toLowerCase()+"%"));
		}
	}

	@Override
	public Predicate toPredicate(Root<Wine> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		fetch(root, query);
		filterByName(root, query, cb);
		filterByPrice(root, query, cb);
		filterByMaker(root, query, cb);
		filterByType(root, query, cb);
		filterByCountry(root, query, cb);
		filterByCapacity(root, query, cb);
		filterByYear(root, query, cb);
		filterByAlcoholContent(root, query, cb);
		Predicate[] array = new Predicate[predicates.size()];
		array = predicates.toArray(array);
		return cb.and(array);
	}

}
