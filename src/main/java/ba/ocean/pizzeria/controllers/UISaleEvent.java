package ba.ocean.pizzeria.controllers;

/**
 * @author 		Almir Pehratovic
 * @version  	0.1
 * @since		05-2015
 * 
 * Form for entering one Sale event in UI.
 */
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
