package ba.ocean.pizzeria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import ba.ocean.jrea.domain.structure.Group;
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
 * Implementation of main service for communicating with database. Every method uses helper repository
 * classes from Spring JPA project.
 */

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
	
	@Autowired
	private IdentificationSetupRepository identificationSetupRepository;
	
	@Autowired
	private GroupRepository groupRepository;


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
	
	@Override
	@Transactional(readOnly=true)
	public List<Group> findAllGroups() {
		return Lists.newArrayList(groupRepository.findAll());
	}
	
	@Override
	@Transactional(readOnly=true)
	public Group findGroupById(int id){
		return groupRepository.findOne(id);
	}


	@Override
	public Group save(Group group) {
		return groupRepository.save(group);
	}

	@Override
	public List<IdentificationSetup> findAllIdentificationSetups() {
		return Lists.newArrayList(identificationSetupRepository.findAll());
	}

	@Override
	public IdentificationSetup save(IdentificationSetup identificationSetup) {
		return identificationSetupRepository.save(identificationSetup);
	}
	
	@Override
	public List<IdentificationSetup> findIdentificationSetupsByEntity(String entity) {
		return identificationSetupRepository.findByEntity(entity);
	}
	
}
