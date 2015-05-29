package ba.ocean.pizzeria.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ba.ocean.jrea.domain.core.Event;
import ba.ocean.pizzeria.behaviour.IdentificationPattern;
import ba.ocean.pizzeria.behaviour.IdentificationSetup;
import ba.ocean.pizzeria.domain.Cash;
import ba.ocean.pizzeria.domain.CashReceipt;
import ba.ocean.pizzeria.domain.Customer;
import ba.ocean.pizzeria.domain.Pizza;
import ba.ocean.pizzeria.domain.Pizzeria;
import ba.ocean.pizzeria.domain.Sale;
import ba.ocean.pizzeria.service.PizzeriaService;

@Controller
@RequestMapping("/pizzeria")
public class PizzeriaController {
	@Autowired
	private PizzeriaService pizzeriaService;
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private Validator validator;
	@Autowired
	private IdentificationPattern identificationPattern;
	
	@RequestMapping(method=RequestMethod.GET)
	public String list(Model uiModel){
		List<Pizza> pizzas = pizzeriaService.findAllPizzas();
		uiModel.addAttribute("pizzas", pizzas);
		List<Cash> cash = pizzeriaService.findAllCash();
		uiModel.addAttribute("cash", cash);
		List<Customer> customers = pizzeriaService.findAllCustomers();
		uiModel.addAttribute("customers", customers);
		List<Sale> sales = pizzeriaService.findAllSales();
		uiModel.addAttribute("sales", sales);
		return "pizzeria/list";
	}
	
	@RequestMapping(value="/pizza/{id}", params="form", method = RequestMethod.GET)
    public String updatePizza(@PathVariable("id") Integer id, Model uiModel){
		Pizza pizza = pizzeriaService.findPizzaById(id);
    	uiModel.addAttribute("pizza", pizza);
    	System.out.println("Pronašao picu " + pizza.getName() + "; broj događaja " + pizza.getEvents().size());
    	return "pizza/update";
    }
	
	@RequestMapping(value="/pizza/{id}", params="form", method = RequestMethod.POST)
    public String updatePizza(@Valid Pizza pizza, BindingResult bindingResult, Model uiModel,
    		HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale){
    	
    	if (bindingResult.hasErrors()){
    		uiModel.addAttribute("message", new Message("errors", messageSource.getMessage("object_save_fail", new Object[]{}, locale)));
    		uiModel.addAttribute("pizza", pizza);
    		return "pizza/update";
    	}
    	
    	System.out.println("Spašavam picu " + pizza.getName() + ", broj događaja " + pizza.getEvents().size());
    	
    	uiModel.asMap().clear();
    	redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("object_save_success",new Object[]{}, locale)));
    	pizzeriaService.save(pizza);
    	
    	return "redirect:/pizzeria";
    }
	
	@RequestMapping(value="/pizza",params="form", method = RequestMethod.GET)
    public String createPizza(Model uiModel){
		Pizza pizza = new Pizza();
		
    	uiModel.addAttribute("pizza", pizza);
    	return "pizza/create";
    }
    
	@RequestMapping(value="/pizza", params="form", method = RequestMethod.POST)
    public String createPizza(@Valid Pizza pizza, BindingResult bindingResult, Model uiModel,
    		HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale){
    	
    	if (bindingResult.hasErrors()){
    		uiModel.addAttribute("message", new Message("errors", messageSource.getMessage("object_save_fail", new Object[]{}, locale)));
    		uiModel.addAttribute("pizza", pizza);
    		return "pizza/update";
    	}
    	
    	System.out.println("Spašavam picu " + pizza.getName() + ", broj događaja " + pizza.getEvents().size());
    	
    	uiModel.asMap().clear();
    	redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("object_save_success",new Object[]{}, locale)));
    	pizzeriaService.save(pizza);
    	
    	return "redirect:/pizzeria";
    }
	
	@RequestMapping(value="/sale",params="form", method = RequestMethod.GET)
    public String createSale(Model uiModel){
		uiModel.addAttribute("form",new UISaleEvent());
    	uiModel.addAttribute("customers",pizzeriaService.findAllCustomers());
    	uiModel.addAttribute("pizzas",pizzeriaService.findAllPizzas());
    	uiModel.addAttribute("cashes",pizzeriaService.findAllCash());
    	return "sale/create";
    }
	
	@RequestMapping(value="/sale", params="form", method = RequestMethod.POST)
    public String createSale(@Valid UISaleEvent form, BindingResult bindingResult, Model uiModel,
    		HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale){
    	
    	if (bindingResult.hasErrors()){
    		uiModel.addAttribute("message", new Message("errors", messageSource.getMessage("object_save_fail", new Object[]{}, locale)));
    		uiModel.addAttribute("form", form);
    		return "sale/create";
    	}
    	
    	Pizzeria pizzeria = pizzeriaService.findAllPizzerias().get(0);
    	Pizza pizza = pizzeriaService.findPizzaById(form.getPizzaId());
    	Customer customer = pizzeriaService.findCustomerById(form.getCustomerId());
    	Cash cash = pizzeriaService.findCashById(form.getCashId());
    	
    	Sale sale = new Sale();
    	
    	sale.setDateTime(new Date());
    	sale.setName("Prodaja pice");
    	System.out.println("Prodaja pice: " + sale.getName());
    	sale.setProvider(pizzeria);
    	sale.setReceiver(customer);
    	sale.setResource(pizza);
    	sale.setQuantity(form.getPizzaQuantity());
    	
    	Set<ConstraintViolation<Sale>> violations = validator.validate(sale);
    	if (violations.size() > 0){
    		String txt = "";
    		for (ConstraintViolation<Sale> v : violations){
    			txt = txt + v.getMessage() + "\n"; 
    		}
    		uiModel.addAttribute("message", new Message("errors", txt));
    		String ret = createSale(uiModel);
    		uiModel.addAttribute("form", form);
    		return ret;
    	}
    	
    	CashReceipt receipt = new CashReceipt();
    	receipt.setDateTime(new Date());
    	receipt.setName("Uplata za picu");
    	receipt.setProvider(customer);
    	receipt.setReceiver(pizzeria);
    	receipt.setResource(cash);
    	receipt.setQuantity(form.getCashQuantity());
    	receipt.addDecrement(sale);
    	
    	uiModel.asMap().clear();
    	redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("object_save_success",new Object[]{}, locale)));
    	
    	pizzeriaService.save(receipt);
    	
    	return "redirect:/pizzeria";
    }
	
	@RequestMapping(value="/setup", method = RequestMethod.GET)
    public String createSetup(Model uiModel){
		UISetupId form = new UISetupId();
		
		Properties props = new Properties();
		try {
			props.load(identificationPattern.getSetupFile().getInputStream());
			form.setPizzaSetup(new IdentificationSetup(props.getProperty("pizza.setup.prefix"), props.getProperty("pizza.setup.pattern"), props.getProperty("pizza.setup.suffix")));
			form.setCashReceiptSetup(new IdentificationSetup(props.getProperty("cashReceipt.setup.prefix"), props.getProperty("cashReceipt.setup.pattern"), props.getProperty("cashReceipt.setup.suffix")));
			uiModel.addAttribute("form",form);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
    	return "config/setup";
    }
	
	@RequestMapping(value="/setup", method = RequestMethod.POST)
    public String createSetup(@Valid UISetupId setup, BindingResult bindingResult, Model uiModel,
    		HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale){
    	
		System.out.println("SPAŠAVAM SETUP");
    	if (bindingResult.hasErrors()){
    		uiModel.addAttribute("message", new Message("errors", messageSource.getMessage("object_save_fail", new Object[]{}, locale)));
    		uiModel.addAttribute("form", setup);
    		return "config/setup";
    	}
    	
    	uiModel.asMap().clear();
    	redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("object_save_success",new Object[]{}, locale)));
    	  	
    	try {
    		Properties props = new Properties();
    		props.setProperty("pizza.setup.prefix",setup.getPizzaSetup().getPrefix());
    		props.setProperty("pizza.setup.pattern",setup.getPizzaSetup().getPattern());
    		props.setProperty("pizza.setup.suffix",setup.getPizzaSetup().getSuffix());
    		props.setProperty("pizza.setup.lastId","1000");
    		props.setProperty("cashReceipt.setup.prefix",setup.getCashReceiptSetup().getPrefix());
    		props.setProperty("cashReceipt.setup.pattern",setup.getCashReceiptSetup().getPattern());
    		props.setProperty("cashReceipt.setup.suffix",setup.getCashReceiptSetup().getSuffix());
    		props.setProperty("cashReceipt.setup.lastId","100");
			File file = identificationPattern.getSetupFile().getFile();
			props.store(new FileOutputStream(file),"setup za identifikacijski pattern");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	return "redirect:/pizzeria";
    }

}
