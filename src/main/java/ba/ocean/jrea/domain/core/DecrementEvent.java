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
@Table(name="decrement")
@PrimaryKeyJoinColumn(name="id")
public class DecrementEvent extends Event{
	private List<IncrementEvent> increments = new ArrayList<IncrementEvent>();
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="increment_decrement", joinColumns=@JoinColumn(name="decrement_id"),
			inverseJoinColumns=@JoinColumn(name="increment_id"))
	public List<IncrementEvent> getIncrements() {
		return increments;
	}

	public void setIncrements(List<IncrementEvent> increments) {
		this.increments = increments;
	}
	
}
