package ua.com.shop.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.dom4j.tree.AbstractEntity;

@Entity
@Table(name = "alcoholContent")
public class AlcoholContent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private double alcoholContent;
	public AlcoholContent(int id, double alcoholContent) {
		this.id = id;
		this.alcoholContent = alcoholContent;
	}
	public AlcoholContent() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getAlcoholContent() {
		return alcoholContent;
	}
	public void setAlcoholContent(double alcoholContent) {
		this.alcoholContent = alcoholContent;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(alcoholContent);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		AlcoholContent other = (AlcoholContent) obj;
		if (Double.doubleToLongBits(alcoholContent) != Double
				.doubleToLongBits(other.alcoholContent))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
}
