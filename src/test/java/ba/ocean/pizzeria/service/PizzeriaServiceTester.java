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
		
	}
	


}
