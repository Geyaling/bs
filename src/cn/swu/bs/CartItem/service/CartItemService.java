package cn.swu.bs.CartItem.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.commons.CommonUtils;
import cn.swu.bs.CartItem.dao.CartItemDao;
import cn.swu.bs.CartItem.domain.CartItem;

public class CartItemService {
	private CartItemDao cartItemDao = new CartItemDao();
	public List<CartItem> loadCartItems(String cartItemIds){
		try {
			return cartItemDao.loadCartItems(cartItemIds);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//修改条目数量
	public CartItem updateQuantity(String cartItemId,int quantity) {
		try {
			cartItemDao.updateQuantity(cartItemId, quantity);
			return cartItemDao.findByCartItemId(cartItemId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//批量删除
	public void batchDelete(String cartItemIds) {
		try {
			cartItemDao.batchDelete(cartItemIds);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//添加条目
	public void add(CartItem cartItem) {
		//1.使用uid和bid去数据库中查询这个条目是否存在
		try {
			CartItem _cartItem = cartItemDao.findByUidAndBid(
					cartItem.getUser().getUid(), cartItem.getBook().getBid());
			if(_cartItem == null) {//如果不存在，添加一个新条目
				cartItem.setCartItemId(CommonUtils.uuid());
				cartItemDao.addCartItem(cartItem);
			}else {//如果存在，修改数量
				int quantity = cartItem.getQuantity()+_cartItem.getQuantity();
				cartItemDao.updateQuantity(_cartItem.getCartItemId(), quantity);
			}
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
		
		
	}
	//我的购物车
	public List<CartItem> myCart(String uid){
		try {
			return cartItemDao.findByUser(uid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
