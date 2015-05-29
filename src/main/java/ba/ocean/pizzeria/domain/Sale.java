package ba.ocean.pizzeria.domain;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import ba.ocean.jrea.domain.core.DecrementEvent;

@Entity
@Table(name="sale")
@PrimaryKeyJoinColumn(name="id")
public class Sale extends DecrementEvent{

}
