package it.splitterizer.holidayservice.domain.command;

import org.springframework.stereotype.Component;

import it.splitterizer.holidayservice.domain.dto.create.HolidayRequest;
import it.splitterizer.holidayservice.domain.dto.create.Request;
import it.splitterizer.holidayservice.domain.mapper.HolidayDataMapper;
import it.splitterizer.holidayservice.domain.ports.output.repository.HolidayRepository;

@Component
public class HolidayCommandFactory implements AbstractCommandFactory<HolidayCommand, Request> {

	public final static String CREATE = "create";
	
	private HolidayRepository holidayRepository;
	private HolidayDataMapper holidayDataMapper;
	
	public HolidayCommandFactory(HolidayRepository holidayRepository, HolidayDataMapper holidayDataMapper) {
		this.holidayRepository = holidayRepository;
		this.holidayDataMapper = holidayDataMapper;
	}

	@Override
	public HolidayCommand createCommand(String type, Request request) {
		return switch(type) {
			case CREATE -> new HolidayCreateCommand(holidayRepository, holidayDataMapper, (HolidayRequest) request);
			default -> throw new IllegalArgumentException("unhandle holiday command type");
		};
	}

}
