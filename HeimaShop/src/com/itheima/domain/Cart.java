package com.itheima.domain;

import java.util.HashMap;
import java.util.Map;

//Cart表示购物车对象
public class Cart {

	//该购物车中存在一个购物项集合
	//这里为了增删改查方便，所以用map，没有用list
	private Map<String,CartItem> cartItems = new HashMap<String,CartItem>();
	
	//购物车的总金额
	private double total;

	public Map<String, CartItem> getCartItems() {
		return cartItems;
	}
	public void setCartItems(Map<String, CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}	
}
