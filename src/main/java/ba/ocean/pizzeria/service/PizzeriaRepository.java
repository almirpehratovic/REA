package ba.ocean.pizzeria.service;

import org.springframework.data.repository.CrudRepository;

import ba.ocean.pizzeria.domain.Pizzeria;

public interface PizzeriaRepository extends CrudRepository<Pizzeria, Integer>{

}
