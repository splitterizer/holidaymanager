package it.splitterizer.holidayservice.domain.ports.output.repository;

import java.util.Optional;

import it.splitterizer.holidayservice.domain.entity.Holiday;
import it.splitterizer.holidayservice.domain.valueobject.TrackingId;

public interface HolidayRepository {

	Holiday save(Holiday holiday);
	
	Optional<Holiday> findByTrackingId(TrackingId trackingId);
	
	
}
