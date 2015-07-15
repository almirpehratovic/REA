package ba.ocean.jrea.domain.structure;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import ba.ocean.jrea.domain.core.Agent;
import ba.ocean.jrea.domain.core.Event;
import ba.ocean.jrea.domain.core.Resource;


/**
 * @author 		Almir Pehratovic
 * @version  	0.1
 * @since		07-2015
 * 
 * Group represents a set of REA entities that have something in common. Rules can apply on groups (for
 * example, discounts)
 */

@Entity
@Table(name="entity_group")
@Inheritance(strategy=InheritanceType.JOINED)
public class Group {
	private int id;
	private String name;
	
	private List<Resource> resources = new ArrayList<Resource>();
	private List<Event> events = new ArrayList<Event>();
	private List<Agent> agents = new ArrayList<Agent>();
	
	@Id
	@GeneratedValue
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
	
	@ManyToMany
	@JoinTable(name="resource_group",joinColumns=@JoinColumn(name="group_id"),inverseJoinColumns=@JoinColumn(name="resource_id"))
	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}
	
	@ManyToMany
	@JoinTable(name="event_group",joinColumns=@JoinColumn(name="group_id"),inverseJoinColumns=@JoinColumn(name="event_id"))	
	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}
	
	@ManyToMany
	@JoinTable(name="agent_group",joinColumns=@JoinColumn(name="group_id"),inverseJoinColumns=@JoinColumn(name="agent_id"))
	public List<Agent> getAgents() {
		return agents;
	}

	public void setAgents(List<Agent> agents) {
		this.agents = agents;
	}

	public String toString() {
		return name;
	}
	
}
