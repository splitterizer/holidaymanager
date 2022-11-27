package it.splitterizer.holidayservice.domain.ports.input.service;

import it.splitterizer.holidayservice.domain.dto.HolidayCreateRequest;
import it.splitterizer.holidayservice.domain.dto.HolidayCreateResponse;
import it.splitterizer.holidayservice.domain.dto.HolidayTrackRequest;
import it.splitterizer.holidayservice.domain.dto.HolidayTrackResponse;

public interface HolidayApplicationService {
	HolidayCreateResponse createHoliday(HolidayCreateRequest holidayRequest);
	HolidayTrackResponse trackHoliday(HolidayTrackRequest holidayTrackRequest);
}
