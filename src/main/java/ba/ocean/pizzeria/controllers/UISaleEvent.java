package ba.ocean.pizzeria.controllers;

public class UISaleEvent {
	private int customerId;
	private int pizzaId;
	private double pizzaQuantity;
	private int cashId;
	private double cashQuantity;
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int receiverId) {
		this.customerId = receiverId;
	}
	public int getPizzaId() {
		return pizzaId;
	}
	public void setPizzaId(int resourceId) {
		this.pizzaId = resourceId;
	}
	public double getPizzaQuantity() {
		return pizzaQuantity;
	}
	public void setPizzaQuantity(double quantity) {
		this.pizzaQuantity = quantity;
	}
	public int getCashId() {
		return cashId;
	}
	public void setCashId(int cashId) {
		this.cashId = cashId;
	}
	public double getCashQuantity() {
		return cashQuantity;
	}
	public void setCashQuantity(double cashQuantity) {
		this.cashQuantity = cashQuantity;
	}
	
	
}
