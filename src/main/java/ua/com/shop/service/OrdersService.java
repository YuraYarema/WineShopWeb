package ua.com.shop.service;

import java.util.List;

import ua.com.shop.entity.Orders;

public interface OrdersService {

	void save(Orders orders);

	Orders findOne(int id);

	List<Orders> findAll();

	void delete(int id);

	void update(Orders orders);
}
