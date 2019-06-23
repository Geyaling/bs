package cn.swu.bs.order.domain;

import java.util.List;

import cn.swu.bs.user.domain.User;

public class Order {
	private String oid;//主键
	private String ordertime;//下单时间
	private double total;//总计
	private int status;
	//订单状态：1 未付款 
	//2 已付款但未发货 
	//3.已发货未确认收获 
	//4.确认收获交易成功 
	//5已取消(未付款才能取消)
	private String address;
	private User owner;
	private List<OrderItem> orderItemList;
	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}
	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
}
