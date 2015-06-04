package ba.ocean.pizzeria.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ba.ocean.pizzeria.domain.Sale;


/**
 * @author 		Almir Pehratovic
 * @version  	0.1
 * @since		05-2015
 * 
 */

public interface SaleRepository extends CrudRepository<Sale, Integer>{
	List<Sale> findByProviderId(Integer id);
}
