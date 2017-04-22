package ua.com.shop.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "wine")
public class Wine  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private double price;
	
	private int version;
	
	@Transient
	private transient MultipartFile file;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maker")
	private Maker maker;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country")
	private Country country;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type")
	private Type type;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "capacity")
	private Capacity capacity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "year")
	private Year year;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "alcoholContent")
	private AlcoholContent alcoholContent;
	
	@ManyToMany
	@JoinTable(name = "wine_orders", joinColumns = @JoinColumn(name = "id_wine"), inverseJoinColumns = @JoinColumn(name = "id_orders"))
	private List<Orders> orders;

	

	public Wine(int id, String name, double price, int version,
			MultipartFile file, Maker maker, Type type, Capacity capacity,
			Year year, AlcoholContent alcoholContent) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.version = version;
		this.file = file;
		this.maker = maker;
		this.type = type;
		this.capacity = capacity;
		this.year = year;
		this.alcoholContent = alcoholContent;
	}

	public Wine() {
	}
	

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

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

	public Maker getMaker() {
		return maker;
	}

	public void setMaker(Maker maker) {
		this.maker = maker;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Wine other = (Wine) obj;
		if (id != other.id)
			return false;
		return true;
	}








	
	

}
