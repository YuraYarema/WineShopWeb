package ua.com.shop.dto.filter;

import java.util.ArrayList;
import java.util.List;

public class WineFilter {
	
	private String search = "";
	
	private String max = "";

	private String min = "";

	private Double maxValue;

	private Double minValue;

	private List<Integer> makerIds = new ArrayList<>();

	private List<Integer> typeIds = new ArrayList<>();
	
	private List<Integer> capacityIds = new ArrayList<>();
	
	private List<Integer> yearIds = new ArrayList<>();
	
	private List<Integer> alcoholContentIds = new ArrayList<>();
	
	private List<Integer> countryIds = new ArrayList<>();

	
	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public Double getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(Double maxValue) {
		this.maxValue = maxValue;
	}

	public Double getMinValue() {
		return minValue;
	}

	public void setMinValue(Double minValue) {
		this.minValue = minValue;
	}

	public List<Integer> getMakerIds() {
		return makerIds;
	}

	public void setMakerIds(List<Integer> makerIds) {
		this.makerIds = makerIds;
	}

	public List<Integer> getTypeIds() {
		return typeIds;
	}

	public void setTypeIds(List<Integer> typeIds) {
		this.typeIds = typeIds;
	}

	public List<Integer> getCapacityIds() {
		return capacityIds;
	}

	public void setCapacityIds(List<Integer> capacityIds) {
		this.capacityIds = capacityIds;
	}

	public List<Integer> getYearIds() {
		return yearIds;
	}

	public void setYearIds(List<Integer> yearIds) {
		this.yearIds = yearIds;
	}

	public List<Integer> getAlcoholContentIds() {
		return alcoholContentIds;
	}

	public void setAlcoholContentIds(List<Integer> alcoholContentIds) {
		this.alcoholContentIds = alcoholContentIds;
	}

	public List<Integer> getCountryIds() {
		return countryIds;
	}

	public void setCountryIds(List<Integer> countryIds) {
		this.countryIds = countryIds;
	}
	

	
	
	
}
