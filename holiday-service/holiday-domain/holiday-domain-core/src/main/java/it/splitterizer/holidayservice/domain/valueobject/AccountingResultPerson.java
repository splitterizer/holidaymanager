package it.splitterizer.holidayservice.domain.valueobject;

import it.splitter.domain.valueobject.Money;

public record AccountingResultPerson(Person person, Person personToPaid, Money toBePaid) {

	public AccountingResultPerson(Person person, Person personToPaid, Money toBePaid) {
		this.person = validateProperty(person, "person cannot be null");
		this.personToPaid = validateProperty(personToPaid, "personToPaid cannot be null");
		this.toBePaid = validateProperty(toBePaid, "toBePaid cannot be null");
	}
	
	private <T> T validateProperty(T property, String errorMsg) {
		if (property == null) {
			throw new IllegalArgumentException(errorMsg);
		}
		return property;
	}
}
