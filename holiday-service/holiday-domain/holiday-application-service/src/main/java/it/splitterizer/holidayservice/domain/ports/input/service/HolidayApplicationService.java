package it.splitterizer.holidayservice.domain.ports.input.service;

import it.splitterizer.holidayservice.domain.dto.create.HolidayRequest;
import it.splitterizer.holidayservice.domain.dto.create.HolidayResponse;

public interface HolidayApplicationService {
	HolidayResponse createHoliday(HolidayRequest holidayRequest);
}
