package ba.ocean.jrea.domain.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Configurable;

@Entity
@Table(name="resource")
@Inheritance(strategy=InheritanceType.JOINED) // Svaka podklasa ide u posebnu DB tabelu
public class Resource implements Serializable{
	private int id;
	private String name;
	private double quantity = 0;
	private String unit;
	
	private List<Event> events = new ArrayList<Event>();
	
	public Resource(){}
	
	public Resource(String name, double quantity, String unit){
		setName(name);
		setQuantity(quantity);
		setUnit(unit);
	}
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@NotEmpty(message="{validation.name.NotEmpty.message}")
	@Size(min=3,max=20,message="{validation.name.Size.message}")
	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="quantity")
	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	
	@Column(name="unit")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	@OneToMany(mappedBy="resource", cascade=CascadeType.ALL, orphanRemoval=false, fetch=FetchType.EAGER)
	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}
	
	public void addEvent(Event event){
		if (!events.contains(event)){
			events.add(event);
		}
	}
	
	@Transient
	public double getStockQuantity(){
		double q = quantity;
		for (Event event : events){
			if (event instanceof IncrementEvent){
				q += event.getQuantity();
			} else if (event instanceof DecrementEvent){
				q -= event.getQuantity();
			}
		}
		return q;
	}

	public String toString(){
		return name + " [total:" + quantity + "]";
	}
}
