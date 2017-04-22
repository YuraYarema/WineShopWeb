package ua.com.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.com.shop.entity.Orders;

public interface OrdersDao extends JpaRepository<Orders, Integer>,JpaSpecificationExecutor<Orders>{

}
