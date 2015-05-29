package ba.ocean.pizzeria.controllers;

import ba.ocean.pizzeria.behaviour.IdentificationSetup;

public class UISetupId {
	private IdentificationSetup pizzaSetup;
	private IdentificationSetup cashReceiptSetup;
	
	public UISetupId() {
		
	}
	
	public IdentificationSetup getPizzaSetup() {
		return pizzaSetup;
	}
	public void setPizzaSetup(IdentificationSetup pizzaSetup) {
		this.pizzaSetup = pizzaSetup;
	}
	public IdentificationSetup getCashReceiptSetup() {
		return cashReceiptSetup;
	}
	public void setCashReceiptSetup(IdentificationSetup cashReceiptSetup) {
		this.cashReceiptSetup = cashReceiptSetup;
	}
	
	
}
