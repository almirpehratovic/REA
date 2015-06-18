package ba.ocean.pizzeria.behaviour;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 		Almir Pehratovic
 * @version  	0.1
 * @since		05-2015
 * 
 * Part of Identification Pattern (Hruby, 159). If pattern is configured as auto-numbered then
 * most important property is lastId. Prefix, pattern and suffix can be configured by application
 * users and are used only when Identification Pattern is not configured as auto-numbered.
 */
@Entity
@Table(name="identification_setup")
public class IdentificationSetup {
	private int id;
	private String entity; 
	private String prefix;
	private String pattern;
	private String suffix;
	private int lastId;
	
	public IdentificationSetup() {
		
	}
	
	public IdentificationSetup(String prefix, String pattern, String suffix) {
		this.prefix = prefix;
		this.pattern = pattern;
		this.suffix = suffix;
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
	
	
	@Column(name="entity")
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}

	@Column(name="prefix")
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	@Column(name="pattern")
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	
	@Column(name="suffix")
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	@Column(name="lastId")
	public int getLastId() {
		return lastId;
	}

	public void setLastId(int lastId) {
		this.lastId = lastId;
	}	
}
