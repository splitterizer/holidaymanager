package it.splitterizer.holidayservice.domain.entity;

import it.splitter.domain.entity.BaseEntity;
import it.splitter.domain.valueobject.Money;
import it.splitterizer.holidayservice.domain.valueobject.PaymentId;
import it.splitterizer.holidayservice.domain.valueobject.Person;

public class Payment extends BaseEntity<PaymentId>{

	private final Person person;
	private final String description;
	private final Money price;
	
	private Payment(Builder builder) {
		super.setId(builder.paymentId);
		this.person = builder.person;
		this.description = builder.description;
		this.price = builder.price;
	}
	
	public void initializePayment(int id) {
		setId(new PaymentId(id));
	}

	public Person getPerson() {
		return person;
	}

	public String getDescription() {
		return description;
	}

	public Money getPrice() {
		return price;
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
		private PaymentId paymentId;
		private Person person;
		private String description;
		private Money price;
		
		public Builder withPaymentId(PaymentId paymentId) {
			this.paymentId = paymentId;
			return this;
		}
		
		public Builder withPerson(Person person) {
			this.person = person;
			return this;
		}
		
		public Builder withDescription(String descrioption) {
			this.description = descrioption;
			return this;
		}
		
		public Builder withPrice(Money price) {
			this.price = price;
			return this;
		}
		
		public Payment build() {
			return new Payment(this);
		}
	}
}
