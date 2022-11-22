package it.splitterizer.holidayservice.domain.entity;

import java.util.List;

import it.splitter.domain.entity.AggregateRoot;
import it.splitter.domain.valueobject.HolidayId;
import it.splitterizer.holidayservice.domain.valueobject.TrackingId;

public class Holiday extends AggregateRoot<HolidayId>{

	private final List<Person> people;
	private final List<Payment> payments;
	private TrackingId trackingId;
	private List<String> failureMessages;
	
	public void initializeHoliday() {
	}
	
	private Holiday(Builder builder) {
		super.setId(builder.holidayId);
		this.people = builder.people;
		this.payments = builder.payments;
		this.trackingId = builder.trackingId;
		this.failureMessages = builder.failureMessages;
	}
	
	public List<Person> getPeople() {
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
	
	public static Builder builder() {
		return new Builder();
	}
	
	
	public static class Builder{
		private HolidayId holidayId;
		private List<Person> people;
		private List<Payment> payments;
		private TrackingId trackingId;
		private List<String> failureMessages;
		
		public Builder withHolidayId(HolidayId holidayId) {
			this.holidayId = holidayId;
			return this;
		}
		
		public Builder withPeople(List<Person> people) {
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
