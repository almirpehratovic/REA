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
		
		//testAop(ctx);
		
		PizzeriaService service = ctx.getBean("pizzeriaService",PizzeriaService.class);
		
		//getAllData(service);
		insertData(service);
		
	}
	
	private static void testAop(ApplicationContext ctx){
		
		
		Pizza pizza = new Pizza();
		pizza.setName("capriccoza");
		System.out.println(pizza.getName());
		
		/*ProxyFactory pf = new ProxyFactory();
		pf.setTarget(pizza);
		IdentificationPattern pattern = ctx.getBean("identificationPattern",IdentificationPattern.class);
		pf.addAdvisor(new DefaultPointcutAdvisor(pattern,pattern));
		
		pizza = (Pizza)pf.getProxy();*/
		pizza.setName("funghi");
		System.out.println(pizza.getName());
	}
	
	
	private static void insertData(PizzeriaService service){
		Customer customer = new Customer();
		customer.setName("Almir");
		customer.setDetails("#34#####");
		
		Pizzeria pizzeria = new Pizzeria();
		pizzeria.setName("Cheers");
		pizzeria.setAddress("Ferhadija 20");
		pizzeria.setDetails("#d.o.o.#1234567890987654####");
		
		Pizza pizza = new Pizza();
		pizza.setName("Capricosa");
		pizza.setQuantity(15);
		pizza.setUnit("kom");
		pizza.setFat("20%");
		pizza.setPackaging("M");
		
		Cash cash = new Cash();
		cash.setName("Konvertibilne marke");
		cash.setUnit("KM");
		cash.setQuantity(150.5);
		cash.setCurrency("BAM");
		
		Sale sale = new Sale();
		sale.setName("Nova Prodaja");
		sale.setDateTime(new Date());
		sale.setResource(pizza);
		sale.setQuantity(1);
		sale.setProvider(pizzeria);
		sale.setReceiver(customer);
		
		CashReceipt receipt = new CashReceipt();
		receipt.setName("Novi prihod");
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
		System.out.println("*** PICE ***");
		for (Pizza p : service.findAllPizzas()){
			print(p);
		}
		
		System.out.println("*** NOVAC *** " );
		for (Cash c : service.findAllCash()){
			print(c);
		}
		
		System.out.println("*** PICERIJE *** " );
		for (Pizzeria p : service.findAllPizzerias()){
			print(p);
		}
		
		System.out.println("*** KUPCI *** " );
		for (Customer c : service.findAllCustomers()){
			print(c);
		}
		
		System.out.println("*** PRODAJE *** " );
		for (Sale s : service.findAllSales()){
			print(s);
		}
		
		System.out.println("*** PRODAJE ZA PICERIJU " + service.findAllPizzerias().get(0) + " *** " );
		for (Sale s : service.findSalesByProviderId(service.findAllPizzerias().get(0).getId())){
			print(s);
		}
		
		System.out.println("*** PRILIV NOVCA *** " );
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
