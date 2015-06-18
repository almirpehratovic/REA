package ba.ocean.pizzeria.service;

import java.util.List;

import ba.ocean.pizzeria.behaviour.IdentificationSetup;
import ba.ocean.pizzeria.domain.Cash;
import ba.ocean.pizzeria.domain.CashReceipt;
import ba.ocean.pizzeria.domain.Customer;
import ba.ocean.pizzeria.domain.Pizza;
import ba.ocean.pizzeria.domain.Pizzeria;
import ba.ocean.pizzeria.domain.Sale;

/**
 * @author 		Almir Pehratovic
 * @version  	0.1
 * @since		05-2015
 * 
 * Main service for communicating with database. All objects that want to work with domain
 * objects should use this service. Main user of the service is PizzeriaController.
 */

public interface PizzeriaService {
	public List<Pizza> findAllPizzas();
	Pizza findPizzaById(int id);
	Pizza save(Pizza pizza);
	
	List<Cash> findAllCash();
	Cash findCashById(int id);
	Cash save(Cash cash);
	
	List<Pizzeria> findAllPizzerias();
	Pizzeria save(Pizzeria pizzeria);
	
	List<Customer> findAllCustomers();
	Customer findCustomerById(int id);
	Customer save(Customer customer);
	
	List<Sale> findAllSales();
	Sale save(Sale sale);
	List<Sale> findSalesByProviderId(Integer id);
	
	List<CashReceipt> findAllCashReceipts();
	CashReceipt save(CashReceipt cashReceipt);
	
	List<IdentificationSetup> findAllIdentificationSetups();
	IdentificationSetup save(IdentificationSetup identificationSetup);
	List<IdentificationSetup> findIdentificationSetupsByEntity(String entity);
}
