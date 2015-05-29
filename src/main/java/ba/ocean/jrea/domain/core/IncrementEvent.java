package ba.ocean.jrea.domain.core;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="increment")
@PrimaryKeyJoinColumn(name="id")
public class IncrementEvent extends Event{
	private List<DecrementEvent> decrements = new ArrayList<DecrementEvent>();
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="increment_decrement", joinColumns=@JoinColumn(name="increment_id"),
			inverseJoinColumns=@JoinColumn(name="decrement_id"))
	public List<DecrementEvent> getDecrements() {
		return decrements;
	}

	public void setDecrements(List<DecrementEvent> decrements) {
		this.decrements = decrements;
	}
	
	public void addDecrement(DecrementEvent decrement){
		decrements.add(decrement);
	}
	
	
	
}
