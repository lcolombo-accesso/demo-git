package com.accesso.horizon.interview;

import java.util.List;

public abstract class Promotion {
	private final String name;
	private final double discountRate;
	protected double totalDiscount = 0;
	public abstract void doApply(List<CartItem> items);

	public void apply(List<CartItem> items) {
		totalDiscount = 0;
		doApply(items);
	}
	
	protected Promotion(String promotionName, double discountRate) {
		this.name = promotionName;
		this.discountRate = discountRate;
	}

	public double getTotalDiscount() {
		return totalDiscount;
	}
	
	protected double getDiscountRate() {
		return discountRate;
	}

	public void increaseTotalDiscount(double totalDiscount) {
		this.totalDiscount += totalDiscount;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return getName() + " = " + getTotalDiscount();
	}
}

class DiscountPromotion extends Promotion {
	public DiscountPromotion(double discountRate) {
		super("Discount on F&B", discountRate);
	}

	@Override
	public void doApply(List<CartItem> items) {
		items.forEach(it -> increaseTotalDiscount(it.applyDiscount(getDiscountRate())));
	}
}

class TicketPromotion extends Promotion {

	public TicketPromotion(double discountRate) {
		super("Buy 3 tickets get 3rd half price", discountRate);
	}
	
	@Override
	public void doApply(List<CartItem> items) {
	}
}
