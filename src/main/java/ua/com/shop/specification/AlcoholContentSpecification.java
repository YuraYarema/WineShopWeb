package ua.com.shop.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.com.shop.dto.filter.AlcoholContentFilter;
import ua.com.shop.dto.filter.CapacityFilter;
import ua.com.shop.entity.AlcoholContent;
import ua.com.shop.entity.Capacity;
import ua.com.shop.service.AlcoholContentService;

public class AlcoholContentSpecification implements
		Specification<AlcoholContent> {

	private final AlcoholContentFilter filter;

	private final List<Predicate> predicates = new ArrayList<>();

	private final static Pattern PATTERN = Pattern
			.compile("^([0-9]{1,17}\\.[0-9]{1,2})|([0-9]{1,17}\\,[0-9]{1,2})|([0-9]{1,17})$");

	public AlcoholContentSpecification(AlcoholContentFilter filter) {
		this.filter = filter;
		if(PATTERN.matcher(filter.getMax()).matches()){
			filter.setMaxValue(Double.valueOf(filter.getMax().replace(',', '.')));
		}
		if(PATTERN.matcher(filter.getMin()).matches()){
			filter.setMinValue(Double.valueOf(filter.getMin().replace(',', '.')));
		}
	}

	private void filterByAlcoholContent(Root<AlcoholContent> root,
			CriteriaQuery<?> query, CriteriaBuilder cb) {

		if (filter.getMaxValue() != null) {
			predicates.add(cb.le(root.get("alcoholContent"),
					filter.getMaxValue()));
		}
		if (filter.getMinValue() != null) {
			predicates.add(cb.ge(root.get("alcoholContent"),
					filter.getMinValue()));
		}
	}

	@Override
	public Predicate toPredicate(Root<AlcoholContent> root,
			CriteriaQuery<?> query, CriteriaBuilder cb) {

		filterByAlcoholContent(root, query, cb);
		if (predicates.isEmpty())
			return null;
		Predicate[] array = new Predicate[predicates.size()];
		array = predicates.toArray(array);
		return cb.and(array);
	}

}
