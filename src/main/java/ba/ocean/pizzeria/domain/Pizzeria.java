package ba.ocean.pizzeria.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Configurable;

import ba.ocean.jrea.domain.core.Agent;

/**
 * @author 		Almir Pehratovic
 * @version  	0.1
 * @since		05-2015
 * 
 * Main economic agent. All process is viewed from Pizzeria's perspective.
 */

@Entity
@DiscriminatorValue(value="ORG")
public class Pizzeria extends Agent {
	
	private String details;
	private String typeOfPizzeria;
	private String jib;
	
	@Column(name="details")
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
		if (details != null){
			setTypeOfPizzeria(details.split("#")[1]);
			setJib(details.split("#")[2]);
		}
	}
	
	@Transient
	public String getTypeOfPizzeria() {
		return typeOfPizzeria;
	}
	public void setTypeOfPizzeria(String typeOfPizzeria) {
		this.typeOfPizzeria = typeOfPizzeria;
	}
	
	@Transient
	public String getJib() {
		return jib;
	}
	public void setJib(String jib) {
		this.jib = jib;
	}
	
	
}
