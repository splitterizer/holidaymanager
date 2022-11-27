package it.splitterizer.holidayservice.domain.command;

public interface AbstractCommandFactory<T, R> {
	public T createCommand(String type, R request);		
}
