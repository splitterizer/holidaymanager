package it.splitterizer.holidayservice.domain.entity;

import it.splitter.domain.entity.BaseEntity;
import it.splitter.domain.valueobject.HolidayId;
import it.splitterizer.holidayservice.domain.valueobject.PersonId;

public class Person extends BaseEntity<PersonId>{
	
	private final HolidayId holidayId;
	private final String name;
	
	public Person(PersonId personId, HolidayId holidayId, String name) {
		super.setId(personId);
		this.holidayId = holidayId;
		this.name = name;
	}

	public HolidayId getHolidayId() {
		return holidayId;
	}

	public String getName() {
		return name;
	}
	
}
