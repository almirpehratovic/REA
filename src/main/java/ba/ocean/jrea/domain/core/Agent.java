package ba.ocean.jrea.domain.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.sound.midi.Receiver;

@Entity
@Table(name="agent")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE) // Sve podklase se nalaze u istoj koloni, razlikuju se po type koloni
@DiscriminatorColumn(name="type")
public class Agent implements Serializable{
	private int id;
	private String name;
	private String address;
	
	private List<Event> providerEvents = new ArrayList<Event>();
	private List<Event> receiverEvents = new ArrayList<Event>();
	
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
	
	@Column(name="address")
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	@OneToMany(mappedBy="provider", cascade=CascadeType.ALL, orphanRemoval=true)
	public List<Event> getProviderEvents() {
		return providerEvents;
	}

	public void setProviderEvents(List<Event> providerEvents) {
		this.providerEvents = providerEvents;
	}
	
	@OneToMany(mappedBy="receiver", cascade=CascadeType.ALL, orphanRemoval=true)
	public List<Event> getReceiverEvents() {
		return receiverEvents;
	}

	public void setReceiverEvents(List<Event> receiverEvents) {
		this.receiverEvents = receiverEvents;
	}
	
	
}
