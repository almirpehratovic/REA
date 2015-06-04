package ba.ocean.pizzeria.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import ba.ocean.jrea.domain.core.Resource;

/**
 * @author 		Almir Pehratovic
 * @version  	0.1
 * @since		05-2015
 * 
 * The process of selling pizza to customers is essentiually an exchange of pizza for cash; Pizzeria
 * gives Pizza to the customer and receives Cash in return. (Hruby, 8).
 */

@Entity
@Table(name="cash")
@PrimaryKeyJoinColumn(name="id")
public class Cash extends Resource{
	private String currency;
	
	@Column(name="currency")
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	
}
