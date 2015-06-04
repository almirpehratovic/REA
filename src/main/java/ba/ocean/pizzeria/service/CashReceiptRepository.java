package ba.ocean.pizzeria.service;

import org.springframework.data.repository.CrudRepository;

import ba.ocean.pizzeria.domain.CashReceipt;

/**
 * @author 		Almir Pehratovic
 * @version  	0.1
 * @since		05-2015
 * 
 */
public interface CashReceiptRepository extends CrudRepository<CashReceipt, Integer>{

}
