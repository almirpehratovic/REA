package ba.ocean.pizzeria.service;

import org.springframework.data.repository.CrudRepository;

import ba.ocean.jrea.domain.core.Resource;


/**
 * @author 		Almir Pehratovic
 * @version  	0.1
 * @since		05-2015
 * 
 */


public interface ResourceRepository extends CrudRepository<Resource, Integer>{

}
