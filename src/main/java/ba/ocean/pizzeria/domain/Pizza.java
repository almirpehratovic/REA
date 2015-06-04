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
 * One of two main economic resources that we want to control.
 * The process of selling pizza to customers is essentiually an exchange of pizza for cash; Pizzeria
 * gives Pizza to the customer and receives Cash in return. (Hruby, 8).
 */

@Entity
@Table(name="pizza")
@PrimaryKeyJoinColumn(name="id")
public class Pizza extends Resource{
	
	private String fat;
	private String packaging;
	
	@Column(name="fat")
	public String getFat() {
		return fat;
	}

	public void setFat(String fat) {
		this.fat = fat;
	}
	
	@Column(name="packaging")
	public String getPackaging() {
		return packaging;
	}

	public void setPackaging(String packaging) {
		this.packaging = packaging;
	}
	
	
}
