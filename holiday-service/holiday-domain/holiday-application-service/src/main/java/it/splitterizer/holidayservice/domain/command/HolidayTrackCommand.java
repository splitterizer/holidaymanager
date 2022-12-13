package it.splitterizer.holidayservice.domain.command;

import java.util.Optional;

import it.splitterizer.holidayservice.domain.dto.HolidayTrackRequest;
import it.splitterizer.holidayservice.domain.dto.HolidayTrackResponse;
import it.splitterizer.holidayservice.domain.entity.Holiday;
import it.splitterizer.holidayservice.domain.mapper.HolidayDataMapper;
import it.splitterizer.holidayservice.domain.ports.output.repository.HolidayRepository;
import it.splitterizer.holidayservice.domain.valueobject.TrackingId;

class HolidayTrackCommand extends HolidayCommand<HolidayTrackResponse> {

	private final HolidayRepository holidayRepository;
	private final HolidayDataMapper holidayDataMapper;
	private final HolidayTrackRequest holidayTrackRequest;
	
	public HolidayTrackCommand(HolidayRepository holidayRepository, 
			HolidayDataMapper holidayDataMapper, HolidayTrackRequest holidayTrackRequest) {
		this.holidayRepository = holidayRepository;
		this.holidayDataMapper = holidayDataMapper;
		this.holidayTrackRequest = holidayTrackRequest;
	}

	@Override
	protected HolidayTrackResponse execute() {
		Optional<Holiday> optHoliday = holidayRepository.findByTrackingId(
				new TrackingId(holidayTrackRequest.getHolidayTrackingId()));

		Holiday holiday = optHoliday.orElseThrow(
				() -> new IllegalArgumentException("Unable to find holiday with id %s"
						.formatted(holidayTrackRequest.getHolidayTrackingId())));
		
		return holidayDataMapper.toHolidayTrackResponse(holiday);
	}

}
