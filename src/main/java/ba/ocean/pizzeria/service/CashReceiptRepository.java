package ba.ocean.pizzeria.service;

import org.springframework.data.repository.CrudRepository;

import ba.ocean.pizzeria.domain.CashReceipt;

public interface CashReceiptRepository extends CrudRepository<CashReceipt, Integer>{

}
