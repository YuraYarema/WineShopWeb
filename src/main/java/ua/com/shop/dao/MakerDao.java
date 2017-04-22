package ua.com.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.com.shop.entity.Maker;

public interface MakerDao extends JpaRepository<Maker, Integer>,JpaSpecificationExecutor<Maker> {
	
	@Query("SELECT i FROM Maker i WHERE i.nameCompany=?1")
	Maker findUnique(String nameCompany);
	


}
