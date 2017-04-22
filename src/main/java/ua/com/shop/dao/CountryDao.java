package ua.com.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.com.shop.entity.Country;

public interface CountryDao extends JpaRepository<Country, Integer>, JpaSpecificationExecutor<Country>{

	@Query("SELECT d FROM Country d WHERE d.country = ?1")
	Country findByCountry(String country);

}
