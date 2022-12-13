package it.splitterizer.holidayservice.domain.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import it.splitter.domain.entity.AggregateRoot;
import it.splitter.domain.valueobject.HolidayId;
import it.splitter.domain.valueobject.Money;
import it.splitterizer.holidayservice.domain.valueobject.Person;
import it.splitterizer.holidayservice.domain.valueobject.TrackingId;

public class Holiday extends AggregateRoot<HolidayId>{

	private final Set<Person> people;
	private final List<Payment> payments;
	private TrackingId trackingId;
	private List<String> failureMessages;
	
	private Holiday(Builder builder) {
		super.setId(builder.holidayId);
		this.people = validateProperty(builder.people, "people property cannot be null");
		this.payments = validateProperty(builder.payments, "payments property cannot be null");
		this.trackingId = builder.trackingId;
		this.failureMessages = builder.failureMessages;
	}
	
	public void initializeHoliday() {
		setId(new HolidayId(UUID.randomUUID()));
		trackingId = new TrackingId(UUID.randomUUID());
	}
	
	public void addPerson(Person person) {
		if (person == null) {
			throw new IllegalArgumentException("person cannot be null");
		}
		
		boolean added = people.add(person);
		
		if (!added) {
			throw new IllegalArgumentException("Unable to add the same person twice");
		}
	}
	
	public void removePerson(Person person) {
		people.remove(person);
	}
	
	public void addPayment(Payment payment) {
		if (payment == null) {
			throw new IllegalArgumentException("payment cannot be null");
		}
		
		payment.initializePayment(payments.size() + 1);
		payments.add(payment);
	}
	
	public void removePayment(Payment payment) {
		payments.remove(payment);
	}
	
	public Money totalExpenses() {
		return payments.stream()
				.map(p -> p.getPrice())
				.reduce(Money.ZERO, Money::add);
	}
	
	public int totalPartecipants() {
		return people.size();
	}
	
	public Money totalPerPerson() {
		BigDecimal totalPartecipants = new BigDecimal(people.size());
		return new Money(totalExpenses().amount().divide(totalPartecipants, 2, RoundingMode.UNNECESSARY));
	}
	
	// GETTER AND SETTER
	
	public Set<Person> getPeople() {
		return people;
	}
	
	public List<Payment> getPayments() {
		return payments;
	}
	
	public TrackingId getTrackingId() {
		return trackingId;
	}
	
	public List<String> getFailureMessages() {
		return failureMessages;
	}
	
	private <P> P validateProperty(P property, String errorMsg) {
		if (property == null) {
			throw new IllegalArgumentException(errorMsg);
		}
		
		return property;
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder{
		private HolidayId holidayId;
		private Set<Person> people;
		private List<Payment> payments;
		private TrackingId trackingId;
		private List<String> failureMessages;
		
		public Builder withHolidayId(HolidayId holidayId) {
			this.holidayId = holidayId;
			return this;
		}
		
		public Builder withPeople(Set<Person> people) {
			this.people = people;
			return this;
		}
		
		public Builder withPayments(List<Payment> payments) {
			this.payments = payments;
			return this;
		}
		
		public Builder withTrackingId(TrackingId trackingId) {
			this.trackingId = trackingId;
			return this;
		}
		
		public Builder withFailureMessages(List<String> failureMessages) {
			this.failureMessages = failureMessages;
			return this;
		}
		
		public Holiday build() {
			return new Holiday(this);
		}
		
	}
	
}
