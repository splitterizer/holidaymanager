package it.splitterizer.holidayservice.domain.command;

import java.util.Optional;

import it.splitterizer.holidayservice.domain.dto.create.HolidayResponse;
import it.splitterizer.holidayservice.domain.dto.create.HolidayTrackRequest;
import it.splitterizer.holidayservice.domain.entity.Holiday;
import it.splitterizer.holidayservice.domain.mapper.HolidayDataMapper;
import it.splitterizer.holidayservice.domain.ports.output.repository.HolidayRepository;
import it.splitterizer.holidayservice.domain.valueobject.TrackingId;

public class HolidayTrackCommand extends HolidayCommand {

	private final HolidayRepository holidayRepository;
	private final HolidayDataMapper holidayDataMapper;
	private final HolidayTrackRequest holidayTrackRequest;
	
	private HolidayTrackCommand(HolidayRepository holidayRepository, 
			HolidayDataMapper holidayDataMapper, HolidayTrackRequest holidayTrackRequest) {
		this.holidayRepository = holidayRepository;
		this.holidayDataMapper = holidayDataMapper;
		this.holidayTrackRequest = holidayTrackRequest;
	}

	@Override
	protected HolidayResponse execute() {
		Optional<Holiday> optHoliday = holidayRepository.findByTrackingId(
				new TrackingId(holidayTrackRequest.getHolidayTrackingId()));

		Holiday holiday = optHoliday.orElseThrow(
				() -> new IllegalArgumentException("Unable to find holiday with id %s"
						.formatted(holidayTrackRequest.getHolidayTrackingId())));
		
		return holidayDataMapper.toHolidayResponse(holiday);
	}

}
