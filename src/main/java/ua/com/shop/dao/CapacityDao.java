package ua.com.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.com.shop.entity.Capacity;

public interface CapacityDao extends JpaRepository<Capacity, Integer>, JpaSpecificationExecutor<Capacity>{

	@Query("SELECT d FROM Capacity d WHERE d.capacity = ?1")
	Capacity findUnique(double capacity);


}
