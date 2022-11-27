package it.splitterizer.holidayservice.domain.command;

import it.splitterizer.holidayservice.domain.dto.create.HolidayResponse;

public abstract class HolidayCommand {
	protected abstract HolidayResponse execute();
}
