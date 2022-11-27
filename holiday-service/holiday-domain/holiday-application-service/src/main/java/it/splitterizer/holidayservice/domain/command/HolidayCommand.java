package it.splitterizer.holidayservice.domain.command;

public abstract class HolidayCommand<R> {
	protected abstract R execute();
}
