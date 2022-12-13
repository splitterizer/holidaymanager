package it.splitterizer.holidayservice.domain.valueobject;

public record Person(String name) {
	
	public Person(String name) {
		if (name == null || name.isBlank()) {
			throw new IllegalArgumentException("Person cannot have an empty or blank name");
		}
		this.name = name;
	}
}
