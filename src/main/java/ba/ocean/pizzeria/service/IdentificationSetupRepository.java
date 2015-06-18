package ba.ocean.pizzeria.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ba.ocean.pizzeria.behaviour.IdentificationSetup;

public interface IdentificationSetupRepository extends CrudRepository<IdentificationSetup, Integer>{
	List<IdentificationSetup> findByEntity(String entity);
}
