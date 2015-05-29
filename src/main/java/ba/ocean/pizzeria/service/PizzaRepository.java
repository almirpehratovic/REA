package ba.ocean.pizzeria.service;

import org.springframework.data.repository.CrudRepository;

import ba.ocean.pizzeria.domain.Pizza;

public interface PizzaRepository extends CrudRepository<Pizza, Integer>{

}
