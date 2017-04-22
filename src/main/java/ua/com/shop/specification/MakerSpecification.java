package ua.com.shop.specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.com.shop.dto.filter.SimpleFilter;
import ua.com.shop.entity.Maker;
import ua.com.shop.entity.Type;

public class MakerSpecification implements Specification<Maker> {

	private final SimpleFilter filter;

	public MakerSpecification(SimpleFilter filter) {
		this.filter = filter;
	}
	
	public Predicate toPredicate(Root<Maker> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if(filter.getSearch().isEmpty())return null;
		return cb.like(cb.lower(root.get("nameCompany")), filter.getSearch().toLowerCase()+"%");
	}


}
