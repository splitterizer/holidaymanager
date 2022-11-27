package it.splitterizer.holidayservice.domain;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import it.splitterizer.holidayservice.domain.command.HolidayCommand;
import it.splitterizer.holidayservice.domain.command.HolidayCommandFactory;
import it.splitterizer.holidayservice.domain.command.HolidayInvoker;
import it.splitterizer.holidayservice.domain.dto.create.HolidayRequest;
import it.splitterizer.holidayservice.domain.dto.create.HolidayResponse;
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
	public HolidayResponse createHoliday(HolidayRequest holidayRequest) {
		HolidayCommand command = commandFactory.createCommand(
				HolidayCommandFactory.CREATE, holidayRequest);
		return holidayInvoker.invoke(command);
	}

}
