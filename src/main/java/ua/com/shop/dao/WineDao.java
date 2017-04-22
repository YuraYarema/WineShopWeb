package ua.com.shop.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.com.shop.entity.Wine;

public interface WineDao extends JpaRepository<Wine, Integer> , JpaSpecificationExecutor<Wine>{

	@Query("SELECT i FROM Wine i WHERE i.name=?1 and i.price=?2 and i.type.id=?3"
			+ " and i.year.id=?4 and i.maker.id=?5 and i.capacity.id=?6 and i.alcoholContent.id=?7 and i.country.id=?8")
	Wine findUnique(String name, double price, int TypeId, int YearId,
			int MakerId, int CapacityId, int AlcoholContentId, int CountryId);

	@Query("SELECT i FROM Wine i LEFT JOIN FETCH i.type LEFT JOIN FETCH i.year"
			+ " LEFT JOIN FETCH i.maker LEFT JOIN FETCH i.capacity LEFT JOIN FETCH i.alcoholContent LEFT JOIN FETCH i.country")
	List<Wine> findAll();

	@Query("SELECT s FROM Wine s LEFT JOIN FETCH s.type LEFT JOIN FETCH s.year"
			+ " LEFT JOIN FETCH s.maker LEFT JOIN FETCH s.capacity LEFT JOIN FETCH s.alcoholContent LEFT JOIN FETCH s.country WHERE s.id=?1")
	Wine findOne(Integer id);
	
	@Query("SELECT i FROM Wine i WHERE i.maker.id = ?1")
	List<Wine> findByWineId(int id);

	@Query("SELECT i FROM Wine i WHERE i.type.id = ?1")
	List<Wine> findByTypeId(int id);
	
	@Query("SELECT i FROM Wine i WHERE i.country.id = ?1")
	List<Wine> findByCountryId(int id);

	@Query("SELECT s FROM Wine s LEFT JOIN FETCH s.type LEFT JOIN FETCH s.year"
			+ " LEFT JOIN FETCH s.maker LEFT JOIN FETCH s.capacity LEFT JOIN FETCH s.alcoholContent LEFT JOIN FETCH s.country WHERE s.id=?1")
	List<Wine> findByCategoriId(int id);
	
	@Query(value="SELECT a FROM Wine a LEFT JOIN FETCH a.maker LEFT JOIN FETCH a.type LEFT JOIN FETCH a.capacity "
			+ "LEFT JOIN FETCH a.year LEFT JOIN FETCH a.alcoholContent LEFT JOIN FETCH a.country",
			countQuery="SELECT count(a.id) FROM Wine a")
	Page<Wine> findAll(Pageable pageable);
	
}
