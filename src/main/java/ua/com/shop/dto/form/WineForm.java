package ua.com.shop.dto.form;



import org.springframework.web.multipart.MultipartFile;

import ua.com.shop.entity.AlcoholContent;
import ua.com.shop.entity.Capacity;
import ua.com.shop.entity.Country;
import ua.com.shop.entity.Maker;
import ua.com.shop.entity.Type;
import ua.com.shop.entity.Year;

public class WineForm {

	private int id;
	private int version;
	private String name;
	private String price;
	private Maker maker;
	private Type type;
	private Capacity capacity;
	private Year year;
	private Country country;
	private AlcoholContent alcoholContent;	
	private transient MultipartFile file;
	
	
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Maker getMaker() {
		return maker;
	}
	public void setMaker(Maker maker) {
		this.maker = maker;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public Capacity getCapacity() {
		return capacity;
	}
	public void setCapacity(Capacity capacity) {
		this.capacity = capacity;
	}
	public Year getYear() {
		return year;
	}
	public void setYear(Year year) {
		this.year = year;
	}
	public AlcoholContent getAlcoholContent() {
		return alcoholContent;
	}
	public void setAlcoholContent(AlcoholContent alcoholContent) {
		this.alcoholContent = alcoholContent;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	
	
	
}
