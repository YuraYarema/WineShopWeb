package ua.com.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.com.shop.entity.Year;

public interface YearDao extends JpaRepository<Year, Integer>, JpaSpecificationExecutor<Year>{

	@Query("SELECT d FROM Year d WHERE d.year = ?1")
	Year findUnique(int year);

}
