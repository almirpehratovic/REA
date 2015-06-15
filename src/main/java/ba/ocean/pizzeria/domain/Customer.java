package ba.ocean.pizzeria.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import ba.ocean.jrea.domain.core.Agent;

/**
 * @author 		Almir Pehratovic
 * @version  	0.1
 * @since		05-2015
 * 
 * Pizzeria's trading partner with whom Pizzeria changes Pizza's with Cash.
 */

@Entity
@Table(name="customer")
@PrimaryKeyJoinColumn(name="id")
public class Customer extends Agent{
	private int age;
	
	@Column(name="age")
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
