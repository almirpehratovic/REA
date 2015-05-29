package ba.ocean.pizzeria.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ba.ocean.pizzeria.domain.Sale;

public interface SaleRepository extends CrudRepository<Sale, Integer>{
	List<Sale> findByProviderId(Integer id);
}
