package cn.swu.bs.order.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;
import cn.swu.bs.book.domain.Book;
import cn.swu.bs.order.domain.Order;
import cn.swu.bs.order.domain.OrderItem;
import cn.swu.bs.pager.Expression;
import cn.swu.bs.pager.PageBean;
import cn.swu.bs.pager.PageConstants;

public class OrderDao {
	private QueryRunner qr = new TxQueryRunner();
	
	//查询订单状态
	public int findStatus(String oid) throws SQLException {
		String sql = "select status from t_order where oid=?";
		Number number = (Number)qr.query(sql,new ScalarHandler(),oid);
		return number.intValue();
	}
	public void updateStatus(String oid,int status) throws SQLException {
		String sql = "update t_order set status=? where oid=?";
		qr.update(sql,status,oid);
	}
	//加载订单
	public Order load(String oid) throws SQLException {
		String sql = "select * from t_order where oid=?";
		Order order = qr.query(sql, new BeanHandler<Order>(Order.class),oid);
		loadOrderItem(order);//为当前订单加载所有订单条目
		return order;
	}
	//生成订单
	public void add(Order order) throws SQLException {
		//插入订单
		String sql = "insert into t_order values(?,?,?,?,?,?)";
		Object[] params = {order.getOid(),order.getOrdertime(),
				order.getTotal(),order.getStatus(),order.getAddress(),
				order.getOwner().getUid()};
		qr.update(sql,params);
		//循环遍历订单的所有条目
		sql = "insert into t_orderitem values(?,?,?,?,?,?,?,?)";
		int len = order.getOrderItemList().size();
		Object[][] objs = new Object[len][];
		for(int i = 0;i<len;i++) {
			OrderItem item = order.getOrderItemList().get(i);
			objs[i] = new Object[]{item.getOrderItemId(),item.getQuantity(),
					item.getSubtotal(),item.getBook().getBid(),
					item.getBook().getBname(),item.getBook().getCurrPrice(),
					item.getBook().getImage_b(),order.getOid()};
		}
		qr.batch(sql, objs);
	}
	//按用户查询订单
	public PageBean<Order> findByUser(String uid,int pc) throws SQLException{
		List<Expression> exprList = new ArrayList<Expression>();
		exprList.add(new Expression("uid","=",uid));
		return findByCriteria(exprList,pc);
	}
	//通用查询方法
	private PageBean<Order> findByCriteria(List<Expression> exprList,int pc) throws SQLException {
		//得到ps,tr,beanlist,创建pagebean
		//1得到ps
		int ps = PageConstants.ORDER_PAGE_SIZE;
		//2通过exprList来生成where子句
		//不影响查询
		StringBuilder whereSql = new StringBuilder(" where 1=1");
		List<Object> params = new ArrayList<Object>();//sql中有问号
		for(Expression expr:exprList) {
			//where 1=1 and bid = 
			whereSql.append(" and ").append(expr.getName())
			.append(" ").append(expr.getOperator()).append("");
			if(!expr.getOperator().equals("is null")) {
				whereSql.append("?");
				params.add(expr.getValue());
			}
		}
		//System.out.println(whereSql);
		//System.out.println(params);
		//总记录数
		String sql = "select count(*) from t_order" + whereSql;
		Number number = (Number)qr.query(sql, new ScalarHandler(),params.toArray());
		int tr = number.intValue();
		//4.得到BeanList
		sql = "select * from t_order"+whereSql+" order by ordertime desc limit ?,?";
		params.add((pc-1)*ps);//第一个问号：（当前页-1）× 每页多少记录
		params.add(ps);
		List<Order> beanList = qr.query(sql, new BeanListHandler<Order>(Order.class)
				,params.toArray());
		for(Order order:beanList) {
			loadOrderItem(order);
		}
		//创建PageBean,设置参数
		PageBean<Order> pb = new PageBean<Order>();
		pb.setBeanList(beanList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);
		//没有url参数
		return pb;
	}
	//为指定order加载他的所有orderItem
	private void loadOrderItem(Order order) throws SQLException {
		//select * from t_orderitem where oid = ?
		String sql = "select * from t_orderitem where oid=?";
		List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler(),order.getOid());
		List<OrderItem> orderItemList = toOrderItemList(mapList);
		order.setOrderItemList(orderItemList);
	}
	private List<OrderItem> toOrderItemList(List<Map<String, Object>> mapList) {
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		for(Map<String,Object> map : mapList) {
			OrderItem orderItem = toOrderItem(map);
			orderItemList.add(orderItem);
		}
		return orderItemList;
	}
	//把一个map转换成OrderItem
	private OrderItem toOrderItem(Map<String, Object> map) {
		OrderItem orderItem = CommonUtils.toBean(map, OrderItem.class);
		Book book = CommonUtils.toBean(map, Book.class);
		orderItem.setBook(book);
		return orderItem;
	}

}
