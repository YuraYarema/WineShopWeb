package ua.com.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.com.shop.entity.Type;

public interface TypeDao extends JpaRepository<Type, Integer>, JpaSpecificationExecutor<Type>{

	@Query("SELECT d FROM Type d WHERE d.type = ?1")
	Type findByName(String type);

}
