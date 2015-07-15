package ba.ocean.jrea.domain.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ba.ocean.jrea.domain.structure.Group;

/**
 * @author 		Almir Pehratovic
 * @version  	0.1
 * @since		05-2015
 * 
 * Agents are individuals (not organizations) capable of controlling economic resources and of
 * transferring or receiving the control to or from other individuals (Hruby, 56)
 */

@Entity
@Table(name="agent")
@Inheritance(strategy=InheritanceType.JOINED)
public class Agent implements Serializable{
	private int id;
	private String name;
	private String address;
	
	private List<Event> providerEvents = new ArrayList<Event>();
	private List<Event> receiverEvents = new ArrayList<Event>();
	
	private List<Group> groups = new ArrayList<Group>();
	
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
	
	@OneToMany(mappedBy="provider", cascade=CascadeType.REFRESH)
	public List<Event> getProviderEvents() {
		return providerEvents;
	}

	public void setProviderEvents(List<Event> providerEvents) {
		this.providerEvents = providerEvents;
	}
	
	@OneToMany(mappedBy="provider", cascade=CascadeType.REFRESH)
	public List<Event> getReceiverEvents() {
		return receiverEvents;
	}

	public void setReceiverEvents(List<Event> receiverEvents) {
		this.receiverEvents = receiverEvents;
	}
	
	@ManyToMany
	@JoinTable(name="agent_group",joinColumns=@JoinColumn(name="agent_id"),inverseJoinColumns=@JoinColumn(name="group_id"))
	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
	
	public void addGroup(Group group) {
		if (!groups.contains(group)) {
			groups.add(group);
		}
	}
	
}
