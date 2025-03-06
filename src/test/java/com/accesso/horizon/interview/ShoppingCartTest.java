package com.accesso.horizon.interview;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class ShoppingCartTest {
	
	@Test
	public void addSameProductToCart_IncreaseQuantity() {
		ShoppingCart cart = new ShoppingCart();

		Product apple = new Product("Apple", 1.0, ProductType.FOOD_AND_BEVERAGE);
		Product ticket = new Product("Concert Ticket", 50.0, ProductType.TICKET);

		cart.addProduct(apple, 2);
		cart.addProduct(ticket, 1);
		cart.addProduct(ticket, 3);
		
		//Add the assertions
		assertEquals(2, cart.getItems().size());
		assertEquals(2, cart.getItems().get(0).getQuantity());
		assertEquals(4, cart.getItems().get(1).getQuantity());
	}
	
	@Test
	public void apply10percentDiscountOnFood() {
		ShoppingCart cart = new ShoppingCart();

		Product apple = new Product("Apple", 1.0, ProductType.FOOD_AND_BEVERAGE);
		Product ticket = new Product("Concert Ticket", 50.0, ProductType.TICKET);

		cart.addProduct(apple, 2);
		cart.addProduct(ticket, 1);

		cart.addPromotion(new DiscountPromotion(0.10));

		assertEquals(51.8, cart.getTotalAmout());
	}
	
	@Test
	public void applyBuy3GetOneHalfPrice_notEnoughTickets() {
		ShoppingCart cart = new ShoppingCart();

		Product apple = new Product("Apple", 1.0, ProductType.FOOD_AND_BEVERAGE);
		Product ticket = new Product("Concert Ticket", 50.0, ProductType.TICKET);
		
		cart.addProduct(apple, 2);
		cart.addProduct(ticket, 2);

		cart.addPromotion(new TicketPromotion(1.00));

		assertEquals(102.00, cart.getTotalAmout());
	}

	@Test
	public void applyBuy3GetOneHalfPrice() {
		ShoppingCart cart = new ShoppingCart();

		Product apple = new Product("Apple", 1.0, ProductType.FOOD_AND_BEVERAGE);
		Product adTicket = new Product("Adult Ticket", 50.0, ProductType.TICKET);
		Product chTicket = new Product("Child Ticket", 20.0, ProductType.TICKET);

		cart.addProduct(apple, 2);
		cart.addProduct(adTicket, 4);
		cart.addProduct(chTicket, 2);

		cart.addPromotion(new TicketPromotion(0.50));

		assertEquals(217.00, cart.getTotalAmout());
	}
	
	@Test
	public void printReceipt() {
		ShoppingCart cart = new ShoppingCart();

		Product meal = new Product("Meal", 15.0, ProductType.FOOD_AND_BEVERAGE);
		Product adTicket = new Product("Adult Ticket", 50.0, ProductType.TICKET);
		Product chTicket = new Product("Child Ticket", 20.0, ProductType.TICKET);
		
		cart.addProduct(meal, 3);
		cart.addProduct(adTicket, 2);
		cart.addProduct(chTicket, 1);

		cart.addPromotion(new DiscountPromotion(0.10));

		cart.printReceipt();
	}


}
