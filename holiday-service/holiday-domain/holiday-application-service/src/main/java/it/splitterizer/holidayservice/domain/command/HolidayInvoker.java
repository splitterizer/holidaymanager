package it.splitterizer.holidayservice.domain.command;

public class HolidayInvoker {

	public <T> T invoke(HolidayCommand<T> command) {
		return command.execute();
	}
}
