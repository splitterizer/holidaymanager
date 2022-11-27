package it.splitterizer.holidayservice.domain.command;

import it.splitterizer.holidayservice.domain.dto.create.HolidayResponse;

public class HolidayInvoker {

	public HolidayResponse invoke(HolidayCommand command) {
		return command.execute();
	}
}
