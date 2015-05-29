package ba.ocean.pizzeria.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import ba.ocean.jrea.domain.core.Resource;

@Entity
@Table(name="cash")
@PrimaryKeyJoinColumn(name="id")
public class Cash extends Resource{
	private String currency;
	
	@Column(name="currency")
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	
}
