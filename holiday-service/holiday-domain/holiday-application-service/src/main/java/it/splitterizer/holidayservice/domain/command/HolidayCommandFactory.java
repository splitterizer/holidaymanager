package it.splitterizer.holidayservice.domain.command;

import org.springframework.stereotype.Component;

import it.splitterizer.holidayservice.domain.dto.HolidayCreateRequest;
import it.splitterizer.holidayservice.domain.dto.HolidayCreateResponse;
import it.splitterizer.holidayservice.domain.dto.Request;
import it.splitterizer.holidayservice.domain.mapper.HolidayDataMapper;
import it.splitterizer.holidayservice.domain.ports.output.repository.HolidayRepository;

@Component
public class HolidayCommandFactory {

	public final static String CREATE = "create";
	public static final String TRACK = "track";
	
	private HolidayRepository holidayRepository;
	private HolidayDataMapper holidayDataMapper;
	
	public HolidayCommandFactory(HolidayRepository holidayRepository, HolidayDataMapper holidayDataMapper) {
		this.holidayRepository = holidayRepository;
		this.holidayDataMapper = holidayDataMapper;
	}

	public <T> HolidayCommand<T> createCommand(String type, Request request) {
		return switch(type) {
			case CREATE -> FactoryRecord.CREATE_HOLIDAY.make(holidayRepository, holidayDataMapper, request);
			case TRACK -> FactoryRecord.TRACK_HOLIDAY.make(holidayRepository, holidayDataMapper, request);
			default -> throw new IllegalArgumentException("unhandle holiday command type");
		};
	}
	
}
