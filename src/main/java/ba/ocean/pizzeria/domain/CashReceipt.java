package ba.ocean.pizzeria.domain;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import ba.ocean.jrea.domain.core.IncrementEvent;

/**
 * @author 		Almir Pehratovic
 * @version  	0.1
 * @since		05-2015
 * 
 * From the perspective of Pizzeria, Cash Receipt is the increment event because this event
 * increments the total value of Cash in the organization.
 */

@Entity
@Table(name="cash_receipt")
@PrimaryKeyJoinColumn(name="id")
public class CashReceipt extends IncrementEvent{

}
