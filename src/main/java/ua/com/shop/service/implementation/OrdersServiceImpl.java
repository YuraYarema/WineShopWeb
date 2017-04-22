package ua.com.shop.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.shop.dao.OrdersDao;
import ua.com.shop.entity.Orders;
import ua.com.shop.service.OrdersService;

@Service
public class OrdersServiceImpl implements OrdersService{

	@Autowired
	private OrdersDao ordersDao;

	@Override
	public void save(Orders orders) {
		ordersDao.save(orders);
	}

	@Override
	public Orders findOne(int id) {
		return ordersDao.findOne(id);
	}

	@Override
	public List<Orders> findAll() {
		return ordersDao.findAll();
	}

	@Override
	public void delete(int id) {
		ordersDao.delete(id);
	}

	@Override
	public void update(Orders orders) {
		ordersDao.save(orders);
	}
}
