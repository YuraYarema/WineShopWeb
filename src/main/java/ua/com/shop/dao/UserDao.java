package ua.com.shop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.com.shop.entity.User;

public interface UserDao extends JpaRepository<User, Integer>{

	User findByEmail(String username);

	@Query("SELECT d FROM User d WHERE d.email = ?1")
	User findByEmails(String email);

}
