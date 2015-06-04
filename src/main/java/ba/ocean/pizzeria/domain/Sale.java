package ba.ocean.pizzeria.domain;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import ba.ocean.jrea.domain.core.DecrementEvent;

/**
 * @author 		Almir Pehratovic
 * @version  	0.1
 * @since		05-2015
 * 
 * From the perspective of Pizzeria, Sale is the decrement event because this event
 * decrements the total value of Pizza's in the organization.
 */

@Entity
@Table(name="sale")
@PrimaryKeyJoinColumn(name="id")
public class Sale extends DecrementEvent{

}
