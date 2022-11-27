package it.splitterizer.holidayservice.domain.entity;

import it.splitter.domain.entity.BaseEntity;
import it.splitter.domain.valueobject.HolidayId;
import it.splitterizer.holidayservice.domain.valueobject.PersonId;

public class Person extends BaseEntity<PersonId>{
	
	private final HolidayId holidayId;
	private final String name;
	
	private Person(Builder builder) {
		super.setId(builder.personId);
		this.holidayId = builder.holidayId;
		this.name = builder.name;
	}

	public HolidayId getHolidayId() {
		return holidayId;
	}

	public String getName() {
		return name;
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
		
		private PersonId personId;
		private HolidayId holidayId;
		private String name;
		
		public Builder withPersonId(PersonId personId) {
			this.personId = personId;
			return this;
		}
		
		public Builder withHolidayId(HolidayId holidayId) {
			this.holidayId = holidayId;
			return this;
		}
		
		public Builder withName(String name) {
			this.name = name;
			return this;
		}
		
		public Person build() {
			return new Person(this);
		}
	}
	
}
