package ba.ocean.pizzeria.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Configurable;

import ba.ocean.jrea.domain.core.Resource;

@Entity
@Table(name="pizza")
@PrimaryKeyJoinColumn(name="id")
public class Pizza extends Resource{
	
	private String fat;
	private String packaging;
	
	@Column(name="fat")
	public String getFat() {
		return fat;
	}

	public void setFat(String fat) {
		this.fat = fat;
	}
	
	@Column(name="packaging")
	public String getPackaging() {
		return packaging;
	}

	public void setPackaging(String packaging) {
		this.packaging = packaging;
	}
	
	
}
