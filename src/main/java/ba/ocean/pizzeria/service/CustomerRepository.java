package ba.ocean.pizzeria.service;

import org.springframework.data.repository.CrudRepository;

import ba.ocean.pizzeria.domain.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer>{

}
