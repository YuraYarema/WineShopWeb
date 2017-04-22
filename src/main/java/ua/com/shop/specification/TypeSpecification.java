package ua.com.shop.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.com.shop.dto.filter.SimpleFilter;
import ua.com.shop.entity.Type;

public class TypeSpecification implements Specification<Type> {

	private final SimpleFilter filter;

	public TypeSpecification(SimpleFilter filter) {
		this.filter = filter;
	}
	
	public Predicate toPredicate(Root<Type> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if(filter.getSearch().isEmpty())return null;
		return cb.like(cb.lower(root.get("type")), filter.getSearch().toLowerCase()+"%");
	}

}
