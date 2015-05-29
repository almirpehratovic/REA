package ba.ocean.pizzeria.domain;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import ba.ocean.jrea.domain.core.IncrementEvent;

@Entity
@Table(name="cash_receipt")
@PrimaryKeyJoinColumn(name="id")
public class CashReceipt extends IncrementEvent{

}
