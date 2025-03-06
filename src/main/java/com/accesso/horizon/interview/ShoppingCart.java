package com.accesso.horizon.interview;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {
	private List<CartItem> items = new ArrayList<>();
	private Map<String, Promotion> promotions = new HashMap<>();

	public void addProduct(Product product, int quantity) {
		items.add(new CartItem(product, quantity));
		applyPromotions();
	}

	private void applyPromotions() {
		promotions.forEach((key, entry) -> entry.apply(items));
	}

	public void addPromotion(Promotion promotion) {
		promotions.put(promotion.getName(), promotion);
		applyPromotions();
	}

	public void sendTransaction() {
		//TODO: ask the candidate how he would structure the message to send the transaction info to the back-end.
	}

	public void printReceipt() {
		//TODO: this method must be refactored by the candidate
		System.out.println("========================================");
		System.out.println(alignIt("Accesso waterpark", 40));
		System.out.println("========================================");
		
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		String formattedDateTime = currentDateTime.format(formatter);
		System.out.println("");
		System.out.println(alignIt(formattedDateTime, 40));
		
		System.out.println("");
		for (CartItem item : items) {
			System.out.println(String.format("%-20s", item.getProduct().getName()) + String.format("%20s", item.getQuantity() + " x " + item.getProduct().getPrice() + " = " + item.getProduct().getPrice() * item.getQuantity()));
			System.out.println(String.format("%40s", -item.getDiscount() + " = " + item.getTotalPrice()));
			System.out.println("");
		}
		
		System.out.println(String.format("%40s", "___________________"));
		System.out.println(String.format("%40s", "Total due    " + getTotalAmout()));

		if (promotions.size() > 0) {
			System.out.println("");
			System.out.println(alignIt("You saved:", 40));
			for (Promotion promo : promotions.values()) {
				System.out.println(String.format("%40s", promo.getName() + " = " + -promo.getTotalDiscount()));
			}
		}
		System.out.println("");
		System.out.println("========================================");
		System.out.println(alignIt("Thank you!", 40));
		System.out.println("========================================");
	}

	private String alignIt(String text, int width) {
		StringBuilder result = new StringBuilder();
		int spaces = (width - text.length()) / 2;
		for (int i = 0; i < spaces; i++) {
			result.append(" ");
		}
		result.append(text);
		
		return result.toString();
	}
	
	public List<CartItem> getItems() {
		return items;
	}

	public Double getTotalAmout() {
		return items.stream().mapToDouble(CartItem::getTotalPrice).sum();
	}
}