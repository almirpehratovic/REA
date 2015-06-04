package ba.ocean.pizzeria.controllers;

import ba.ocean.pizzeria.behaviour.IdentificationSetup;

/**
 * @author 		Almir Pehratovic
 * @version  	0.1
 * @since		05-2015
 * 
 * Responsible for entering Identification Pattern setup in UI.
 */
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
