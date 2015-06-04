package ba.ocean.pizzeria.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
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
@DiscriminatorValue(value="FL")
public class Customer extends Agent{
	private String details;
	private int age;
	
	@Column(name="details")
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
		if (details != null)
			setAge(Integer.parseInt(details.split("#")[1]));
	}
	
	@Transient
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
