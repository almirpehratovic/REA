package ba.ocean.pizzeria.service;

import java.util.Date;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import ba.ocean.jrea.domain.core.Agent;
import ba.ocean.jrea.domain.core.Event;
import ba.ocean.jrea.domain.core.Resource;
import ba.ocean.pizzeria.behaviour.IdentificationPattern;
import ba.ocean.pizzeria.domain.Cash;
import ba.ocean.pizzeria.domain.CashReceipt;
import ba.ocean.pizzeria.domain.Customer;
import ba.ocean.pizzeria.domain.Pizza;
import ba.ocean.pizzeria.domain.Pizzeria;
import ba.ocean.pizzeria.domain.Sale;

public class PizzeriaServiceTester {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:META-INF/spring/pizzeria-app-context.xml");
		ctx.refresh();
		
		PizzeriaService service = ctx.getBean("pizzeriaService",PizzeriaService.class);
		
		insertData(service);
		
	}
	
	
	private static void insertData(PizzeriaService service){
		Customer customer = new Customer();
		customer.setName("John Doe");
		customer.setDetails("#34#####");
		
		Pizzeria pizzeria = new Pizzeria();
		pizzeria.setName("Cheers");
		pizzeria.setAddress("Unknown");
		pizzeria.setDetails("#d.o.o.#1234567890987654####");
		
		Pizza pizza = new Pizza();
		pizza.setName("Funghi");
		pizza.setQuantity(15);
		pizza.setUnit("kom");
		pizza.setFat("20%");
		pizza.setPackaging("M");
		
		Cash cash = new Cash();
		cash.setName("US Dollars");
		cash.setUnit("$");
		cash.setQuantity(150.5);
		cash.setCurrency("USD");
		
		Sale sale = new Sale();
		sale.setName("New Sale");
		sale.setDateTime(new Date());
		sale.setResource(pizza);
		sale.setQuantity(1);
		sale.setProvider(pizzeria);
		sale.setReceiver(customer);
		
		CashReceipt receipt = new CashReceipt();
		receipt.setName("New Cash Receipt");
		receipt.setProvider(customer);
		receipt.setReceiver(pizzeria);
		receipt.setResource(cash);
		receipt.setQuantity(2.5);
		receipt.addDecrement(sale);
		receipt.getProvider().setAddress("AAABBB");
		
		// 1 SAVE
		service.save(receipt);
	}
	
	private static void getAllData(PizzeriaService service){
		System.out.println("*** PIZZA ***");
		for (Pizza p : service.findAllPizzas()){
			print(p);
		}
		
		System.out.println("*** CASH *** " );
		for (Cash c : service.findAllCash()){
			print(c);
		}
		
		System.out.println("*** PIZZERIA *** " );
		for (Pizzeria p : service.findAllPizzerias()){
			print(p);
		}
		
		System.out.println("*** CUSTOMER *** " );
		for (Customer c : service.findAllCustomers()){
			print(c);
		}
		
		System.out.println("*** SALE *** " );
		for (Sale s : service.findAllSales()){
			print(s);
		}
		
		System.out.println("*** SALES PER PIZZERIA " + service.findAllPizzerias().get(0) + " *** " );
		for (Sale s : service.findSalesByProviderId(service.findAllPizzerias().get(0).getId())){
			print(s);
		}
		
		System.out.println("*** CASH RECEIPT *** " );
		for (CashReceipt c : service.findAllCashReceipts()){
			print(c);
		}
	}
	
	private static void print(Event event){
		System.out.println("event id: " + event.getId());
		System.out.println("name: " + event.getName());
		System.out.println("date: " + event.getDateTime());
		System.out.println("resource: " + event.getResource());
	}
	
	private static void print(Agent agent){
		System.out.println("agent id: " + agent.getId());
		System.out.println("name: " + agent.getName());
		System.out.println("address: " + agent.getAddress());
		
		if (agent instanceof Pizzeria){
			Pizzeria p = (Pizzeria)agent;
			System.out.println("* type: " + p.getTypeOfPizzeria());
			System.out.println("* jib: " + p.getJib());
		}
		if (agent instanceof Customer){
			Customer c = (Customer)agent;
			System.out.println("* age: " + c.getAge());
		}
		
		System.out.println();
	}
	
	private static void print(Resource resource){
		
		System.out.println("resource id: " + resource.getId());
		System.out.println("name: " + resource.getName());
		System.out.println("initial quantity: " + resource.getQuantity() + " " + resource.getUnit());
		System.out.println("stock quantity: " + resource.getStockQuantity() + " " + resource.getUnit());
		
		
		if (resource instanceof Pizza){
			Pizza p = (Pizza)resource;
			System.out.println("* " + p.getPackaging() + "/" + p.getFat());
		}
		if (resource instanceof Cash){
			Cash c = (Cash)resource;
			System.out.println("* " + c.getCurrency());
		}
		
		//System.out.println("events: " + resource.getEvents().size());
		
		System.out.println();
	}

}
