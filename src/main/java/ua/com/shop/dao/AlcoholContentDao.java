package ua.com.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.com.shop.entity.AlcoholContent;
import ua.com.shop.entity.Capacity;

public interface AlcoholContentDao extends
		JpaRepository<AlcoholContent, Integer>,
		JpaSpecificationExecutor<AlcoholContent> {

	@Query("SELECT d FROM AlcoholContent d WHERE d.alcoholContent = ?1")
	AlcoholContent findUnique(double alcoholContent);

}
