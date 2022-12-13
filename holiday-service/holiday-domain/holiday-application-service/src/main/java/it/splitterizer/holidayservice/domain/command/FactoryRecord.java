package it.splitterizer.holidayservice.domain.command;

import it.splitterizer.holidayservice.domain.dto.HolidayCreateRequest;
import it.splitterizer.holidayservice.domain.dto.HolidayCreateResponse;
import it.splitterizer.holidayservice.domain.dto.HolidayTrackRequest;
import it.splitterizer.holidayservice.domain.dto.HolidayTrackResponse;
import it.splitterizer.holidayservice.domain.dto.Request;
import it.splitterizer.holidayservice.domain.mapper.HolidayDataMapper;
import it.splitterizer.holidayservice.domain.ports.output.repository.HolidayRepository;
import it.splitterizer.holidayservice.domain.command.HolidayTrackCommand;

enum FactoryRecord {

	CREATE_HOLIDAY {

		@Override
		public HolidayCommand<HolidayCreateResponse> make(HolidayRepository rep, HolidayDataMapper dataMapper, Request request) {
			return new HolidayCreateCommand(rep, dataMapper, (HolidayCreateRequest) request);
		}
		
	},
	
	TRACK_HOLIDAY {

		@Override
		public HolidayCommand<HolidayTrackResponse> make(HolidayRepository rep, HolidayDataMapper dataMapper, Request request) {
			return new HolidayTrackCommand(rep, dataMapper, (HolidayTrackRequest) request);
		}
		
	};
	
	public abstract <T> T make(HolidayRepository rep, HolidayDataMapper dataMapper, Request request); 
}
