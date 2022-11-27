package it.splitterizer.holidayservice.domain.command;

import it.splitterizer.holidayservice.domain.dto.create.HolidayRequest;
import it.splitterizer.holidayservice.domain.dto.create.HolidayResponse;
import it.splitterizer.holidayservice.domain.entity.Holiday;
import it.splitterizer.holidayservice.domain.mapper.HolidayDataMapper;
import it.splitterizer.holidayservice.domain.ports.output.repository.HolidayRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class HolidayCreateCommand extends HolidayCommand{

	private final HolidayRepository holidayRepository;
	private final HolidayDataMapper holidayDataMapper;
	private final HolidayRequest holidayRequest;
	
	public HolidayCreateCommand(HolidayRepository holidayRepository, 
			HolidayDataMapper holidayDataMapper,
			HolidayRequest holidayRequest) {
		super();
		this.holidayRepository = holidayRepository;
		this.holidayDataMapper = holidayDataMapper;
		this.holidayRequest = holidayRequest;
	}

	@Override
	protected HolidayResponse execute() {
		Holiday holiday = holidayDataMapper.toHoliday(holidayRequest);
		holiday = holidayRepository.save(holiday);
		log.info("Holiday is created with id: {}", holiday.getId().getValue());
		return holidayDataMapper.toHolidayResponse(holiday);
	}

}
