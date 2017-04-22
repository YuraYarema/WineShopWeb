package ua.com.shop.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.com.shop.dto.filter.YearFilter;
import ua.com.shop.entity.Capacity;
import ua.com.shop.entity.Year;

public class YearSpecification implements Specification<Year> {

	private final YearFilter filter;

	private final List<Predicate> predicates = new ArrayList<>();

	private final static Pattern PATTERN = Pattern
			.compile("^([0-9]{1,17}\\.[0-9]{1,2})|([0-9]{1,17}\\,[0-9]{1,2})|([0-9]{1,17})$");

	public YearSpecification(YearFilter filter) {
		this.filter = filter;
		if (PATTERN.matcher(filter.getMax()).matches()) {
			filter.setMaxValue(Integer.valueOf(filter.getMax()));
		}
		if (PATTERN.matcher(filter.getMin()).matches()) {
			filter.setMinValue(Integer.valueOf(filter.getMin()));
		}
	}

	private void filterByYear(Root<Year> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {

		if (filter.getMaxValue() != null) {
			predicates.add(cb.le(root.get("year"), filter.getMaxValue()));
		}
		if (filter.getMinValue() != null) {
			predicates.add(cb.ge(root.get("year"), filter.getMinValue()));
		}
	}

	@Override
	public Predicate toPredicate(Root<Year> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		filterByYear(root, query, cb);
		if(predicates.isEmpty())return null;
		Predicate[] array = new Predicate[predicates.size()];
		array = predicates.toArray(array);
		return cb.and(array);
	}

}
