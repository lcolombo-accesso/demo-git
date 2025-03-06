package com.accesso.horizon.interview;

public class CartItem {
	private Product product;
	private int quantity;
	private double totalPrice;
	private double discount;

	public CartItem(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
		this.totalPrice = product.getPrice() * quantity;
	}

	public void increaseQuantity(int quantity) {
		this.quantity += quantity;
		this.totalPrice += product.getPrice() * quantity;
	}

	public double applyDiscount(double discountRate) {
		discount = this.totalPrice * discountRate;
		this.totalPrice -= getDiscount();
		return getDiscount();
	}

	public double applyDiscountOnQuantity(double discountRate, int discountableQuantity) {
		discount = product.getPrice() * discountRate * discountableQuantity;
		this.totalPrice -= getDiscount();
		return getDiscount();
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	@Override
	public String toString() {
		return product.getName() + " x " + quantity + " = " + totalPrice;
	}

	public double getDiscount() {
		return discount;
	}
}
