package it.splitterizer.holidayservice.domain.event;

import java.time.ZonedDateTime;

import it.splitter.domain.event.DomainEvent;
import it.splitterizer.holidayservice.domain.entity.Holiday;

public class HolidayCreateEvent implements DomainEvent<Holiday>{

	private Holiday holiday;
	private ZonedDateTime zonedDateTime;
	
	public Holiday getHoliday() {
		return holiday;
	}
	
	public ZonedDateTime getZonedDateTime() {
		return zonedDateTime;
	}
	
	
}
