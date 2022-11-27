package it.splitterizer.holidayservice.domain;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import it.splitterizer.holidayservice.domain.command.HolidayCommand;
import it.splitterizer.holidayservice.domain.command.HolidayCommandFactory;
import it.splitterizer.holidayservice.domain.command.HolidayInvoker;
import it.splitterizer.holidayservice.domain.dto.HolidayCreateRequest;
import it.splitterizer.holidayservice.domain.dto.HolidayCreateResponse;
import it.splitterizer.holidayservice.domain.dto.HolidayTrackRequest;
import it.splitterizer.holidayservice.domain.dto.HolidayTrackResponse;
import it.splitterizer.holidayservice.domain.ports.input.service.HolidayApplicationService;

@Validated
@Service
class HolidayApplicationServiceImpl implements HolidayApplicationService {
	
	private final HolidayCommandFactory commandFactory;
	private final HolidayInvoker holidayInvoker;
	
	public HolidayApplicationServiceImpl(HolidayCommandFactory commandFactory, HolidayInvoker holidayInvoker) {
		this.commandFactory = commandFactory;
		this.holidayInvoker = holidayInvoker;
	}

	@Override
	public HolidayCreateResponse createHoliday(HolidayCreateRequest holidayRequest) {
		HolidayCommand<HolidayCreateResponse> command = commandFactory.createCommand(
				HolidayCommandFactory.CREATE, holidayRequest);
		return holidayInvoker.invoke(command);
	}

	@Override
	public HolidayTrackResponse trackHoliday(HolidayTrackRequest holidayTrackRequest) {
		HolidayCommand<HolidayTrackResponse> command = commandFactory.createCommand(
				HolidayCommandFactory.TRACK, holidayTrackRequest);
		return holidayInvoker.invoke(command);
	}

}
