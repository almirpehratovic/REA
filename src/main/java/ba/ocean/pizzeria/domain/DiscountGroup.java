package ba.ocean.pizzeria.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import ba.ocean.jrea.domain.structure.Group;

@Entity
@Table(name="discount_group")
@PrimaryKeyJoinColumn(name="id")
public class DiscountGroup extends Group{
	private double discountPercent;
	
	@Column(name="discount_percent")
	public double getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(double discountPercent) {
		this.discountPercent = discountPercent;
	}
	
	
}
