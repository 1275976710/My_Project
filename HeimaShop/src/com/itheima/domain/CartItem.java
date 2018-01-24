package com.itheima.domain;

//CartItem表示购物车当中的每一个购物项
public class CartItem {

	//每一个购物项对应一个唯一的商品
	private Product product;
	//该购物项中的商品购买数量
	private int buyNum;
	//该购物项的小计
	private double subtotal;
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getBuyNum() {
		return buyNum;
	}
	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	
	
	
}
