package ba.ocean.pizzeria.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import ba.ocean.jrea.domain.core.Agent;
import ba.ocean.jrea.domain.core.Resource;
import ba.ocean.pizzeria.domain.Cash;
import ba.ocean.pizzeria.domain.CashReceipt;
import ba.ocean.pizzeria.domain.Customer;
import ba.ocean.pizzeria.domain.Pizza;
import ba.ocean.pizzeria.domain.Pizzeria;
import ba.ocean.pizzeria.domain.Sale;

@Repository
@Transactional
@Service("pizzeriaService")
public class PizzeriaServiceImpl implements PizzeriaService{
	
	@Autowired
	private PizzaRepository pizzaRepository;
	
	@Autowired
	private CashRepository cashRepository;
	
	@Autowired
	private PizzeriaRepository pizzeriaRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private SaleRepository saleRepository;
	
	@Autowired
	private CashReceiptRepository cashReceiptRepository;


	@Override
	@Transactional(readOnly=true)
	public List<Pizza> findAllPizzas() {
		return Lists.newArrayList(pizzaRepository.findAll());
	}
	
	@Override
	@Transactional(readOnly=true)
	public Pizza findPizzaById(int id){
		return pizzaRepository.findOne(id);
	}


	@Override
	public Pizza save(Pizza pizza) {
		return pizzaRepository.save(pizza);
	}


	@Override
	@Transactional(readOnly=true)
	public List<Cash> findAllCash() {
		return Lists.newArrayList(cashRepository.findAll());
	}
	
	@Override
	@Transactional(readOnly=true)
	public Cash findCashById(int id){
		return cashRepository.findOne(id);
	}

	@Override
	public Cash save(Cash cash) {
		return cashRepository.save(cash);
	}


	@Override
	@Transactional(readOnly=true)
	public List<Pizzeria> findAllPizzerias() {
		return Lists.newArrayList(pizzeriaRepository.findAll());
	}


	@Override
	@Transactional(readOnly=true)
	public Pizzeria save(Pizzeria pizzeria) {
		return pizzeriaRepository.save(pizzeria);
	}


	@Override
	@Transactional(readOnly=true)
	public List<Customer> findAllCustomers() {
		return Lists.newArrayList(customerRepository.findAll());
	}
	
	@Override
	@Transactional(readOnly=true)
	public Customer findCustomerById(int id){
		return customerRepository.findOne(id);
	}

	@Override
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}


	@Override
	@Transactional(readOnly=true)
	public List<Sale> findAllSales() {
		return Lists.newArrayList(saleRepository.findAll());
	}


	@Override
	@Transactional(readOnly=true)
	public Sale save(Sale sale) {
		return saleRepository.save(sale);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Sale> findSalesByProviderId(Integer id){
		return saleRepository.findByProviderId(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<CashReceipt> findAllCashReceipts(){
		return Lists.newArrayList(cashReceiptRepository.findAll());
	}
	
	@Override
	public CashReceipt save(CashReceipt cashReceipt) {
		return cashReceiptRepository.save(cashReceipt);
	}
	
	

}
