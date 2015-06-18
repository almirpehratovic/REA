package ba.ocean.pizzeria.controllers;

import java.util.ArrayList;
import java.util.List;

import ba.ocean.pizzeria.behaviour.IdentificationSetup;

/**
 * @author 		Almir Pehratovic
 * @version  	0.1
 * @since		05-2015
 * 
 * Responsible for entering Identification Pattern setup in UI.
 */
public class UISetupId {
	
	private List<IdentificationSetup> setups;// = new ArrayList<IdentificationSetup>();

	public List<IdentificationSetup> getSetups() {
		return setups;
	}

	public void setSetups(List<IdentificationSetup> setups) {
		this.setups = setups;
	}
	
	
	
	/*private IdentificationSetup pizzaSetup;
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
	}*/
	
	
}
