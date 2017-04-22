package ua.com.shop.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.dom4j.tree.AbstractEntity;


@Entity
@Table(name = "capacity")
public class Capacity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private double capacity;
	
	public Capacity() {
	}

	public Capacity(int id, double capacity) {
		this.id = id;
		this.capacity = capacity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(capacity);
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
		Capacity other = (Capacity) obj;
		if (Double.doubleToLongBits(capacity) != Double
				.doubleToLongBits(other.capacity))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	

	
	
}
