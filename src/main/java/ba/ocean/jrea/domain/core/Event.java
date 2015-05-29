package ba.ocean.jrea.domain.core;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.AssertTrue;

@Entity
@Table(name="event")
@Inheritance(strategy=InheritanceType.JOINED)
public class Event implements Serializable{
	private int id;
	private String name;
	private Date dateTime;
	
	private Agent provider;
	private Agent receiver;
	private Resource resource;
	private double quantity;
	
	public Event(){
	}
	
	public Event(String name){
		this.name = name;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="event_date")
	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="provider_id")
	public Agent getProvider() {
		return provider;
	}
	
	public void setProvider(Agent provider) {
		this.provider = provider;
	}
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="receiver_id")
	public Agent getReceiver() {
		return receiver;
	}

	public void setReceiver(Agent receiver) {
		this.receiver = receiver;
	}
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="resource_id")
	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}
	
	@Column(name="quantity")
	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	
	@Transient
	@AssertTrue(message="{validation.event.resource.available.message}")
	public boolean isResourceAvailable(){
		return resource.getStockQuantity() > 0;
	}

	public String toString(){
		/*return "event: " + name + "(resource: " + resource + ", quantity: " + quantity
				+ ", provider: " + provider + ", receiver: " + receiver + ")";*/
		return name ;
	}
}
