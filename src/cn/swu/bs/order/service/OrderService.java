package cn.swu.bs.order.service;

import java.sql.SQLException;

import cn.itcast.jdbc.JdbcUtils;
import cn.swu.bs.order.dao.OrderDao;
import cn.swu.bs.order.domain.Order;
import cn.swu.bs.pager.PageBean;

public class OrderService {
	private OrderDao orderDao = new OrderDao();
	public int findStatus(String oid) {
		try {
			return orderDao.findStatus(oid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void updateStatus(String oid,int status) {
		try {
			orderDao.updateStatus(oid,status);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}		
	}
	public Order load(String oid) {
		try {
			JdbcUtils.beginTransaction();
			Order order = orderDao.load(oid);
			JdbcUtils.commitTransaction();
			return order;
		} catch (SQLException e) {
			try {
				JdbcUtils.rollbackTransaction();
			}catch(SQLException e1){}
			throw new RuntimeException(e);
		}		
	}
	//生成订单
	public void createOrder(Order order){
		try {
			JdbcUtils.beginTransaction();
			orderDao.add(order);
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils.rollbackTransaction();
			}catch(SQLException e1){}
			throw new RuntimeException(e);
		}
	}
	
	//我的订单 事务处理
	public PageBean<Order> myOrders(String uid,int pc){
		try {
			JdbcUtils.beginTransaction();
			PageBean<Order> pb = orderDao.findByUser(uid,pc);
			JdbcUtils.commitTransaction();
			return pb;
		} catch (SQLException e) {
			try {
				JdbcUtils.rollbackTransaction();
			}catch(SQLException e1){}
			throw new RuntimeException(e);
		}
	}
}
