package ba.ocean.pizzeria.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
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
//@DiscriminatorValue(value="ORG")
@Table(name="pizzeria")
@PrimaryKeyJoinColumn(name="id")
public class Pizzeria extends Agent {
	
	private int numOfEmployees;

	@Column(name="numOfEmployees")
	public int getNumOfEmployees() {
		return numOfEmployees;
	}

	public void setNumOfEmployees(int numOfEmployees) {
		this.numOfEmployees = numOfEmployees;
	}
	
	
}
