package it.splitterizer.holidayservice.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import it.splitter.domain.valueobject.HolidayId;
import it.splitter.domain.valueobject.Money;
import it.splitterizer.holidayservice.domain.command.HolidayCommand;
import it.splitterizer.holidayservice.domain.command.HolidayCommandFactory;
import it.splitterizer.holidayservice.domain.command.HolidayInvoker;
import it.splitterizer.holidayservice.domain.command.HolidayTrackCommand;
import it.splitterizer.holidayservice.domain.dto.HolidayCreateRequest;
import it.splitterizer.holidayservice.domain.dto.HolidayCreateResponse;
import it.splitterizer.holidayservice.domain.dto.HolidayTrackRequest;
import it.splitterizer.holidayservice.domain.dto.HolidayTrackResponse;
import it.splitterizer.holidayservice.domain.dto.Payment;
import it.splitterizer.holidayservice.domain.dto.Person;
import it.splitterizer.holidayservice.domain.entity.Holiday;
import it.splitterizer.holidayservice.domain.mapper.HolidayDataMapper;
import it.splitterizer.holidayservice.domain.ports.output.repository.HolidayRepository;
import it.splitterizer.holidayservice.domain.valueobject.TrackingId;

public class HolidayApplicationServiceImplTest {
	
	private static final String HOLIDAY_TRACKING_ID_STR = "holidayTrackingId";

	private HolidayApplicationServiceImpl applicationServiceImpl;
	private HolidayRepository holidayRepository;
	private HolidayCommandFactory commandFactory;
	private HolidayInvoker holidayInvoker;

	@BeforeEach
	void setupEach() {
		HolidayDataMapper dataMapper = new HolidayDataMapper();
		holidayRepository = mock(HolidayRepository.class);
		holidayInvoker = mock(HolidayInvoker.class);
		commandFactory = mock(HolidayCommandFactory.class);
		applicationServiceImpl = new HolidayApplicationServiceImpl(commandFactory, holidayInvoker);
	}

	@DisplayName("createHoliday: call create command once")
	@Test
	void createHolidayShouldCallCreateCommandOnce() {
		Holiday holiday = createHoliday();
		HolidayCreateRequest holidayRequest = createHolidayRequest();
		HolidayCommand command = mock(HolidayCommand.class);
		when(holidayRepository.save(any())).thenReturn(holiday);
		when(commandFactory.createCommand(HolidayCommandFactory.CREATE, holidayRequest)).thenReturn(command);
		applicationServiceImpl.createHoliday(holidayRequest);
		verify(commandFactory).createCommand(HolidayCommandFactory.CREATE, holidayRequest);
	}

	@DisplayName("createHoliday: should call invoke once")
	@Test
	void createHolidayShouldCallInvokeOnce() {
		Holiday holiday = createHoliday();
		HolidayCreateRequest holidayRequest = createHolidayRequest();
		HolidayCommand command = mock(HolidayCommand.class);
		when(holidayRepository.save(any())).thenReturn(holiday);
		when(commandFactory.createCommand(HolidayCommandFactory.CREATE, holidayRequest)).thenReturn(command);
		applicationServiceImpl.createHoliday(holidayRequest);
		verify(holidayInvoker).invoke(command);
	}
	
	@DisplayName("createHoliday: should return HolidayResponse")
	@Test
	void createHolidayShouldReturnHolidayResponse() {
		HolidayCreateRequest holidayRequest = createHolidayRequest();
		HolidayCreateResponse expectedResponse = createCreateResponse();
		when(holidayInvoker.invoke(any())).thenReturn(createCreateResponse());
		HolidayCreateResponse response = applicationServiceImpl.createHoliday(holidayRequest);
		assertEquals(expectedResponse.getHolidayTrackingId(), response.getHolidayTrackingId());
	}
	
	@DisplayName("trackHoliday: call create command once")
	@Test
	void trackHolidayShouldCallCreateCommandOnce() {
		Holiday holiday = createHoliday();
		HolidayTrackRequest request = createHolidayTrackRequest();
		HolidayCommand command = mock(HolidayTrackCommand.class);
		when(commandFactory.createCommand(HolidayCommandFactory.TRACK, request)).thenReturn(command);
		applicationServiceImpl.trackHoliday(request);
		verify(commandFactory).createCommand(HolidayCommandFactory.TRACK, request);
	}
	
	@DisplayName("trackHoliday: should call invoke once")
	@Test
	void trackHolidayShouldCallInvokeOnce() {
		Holiday holiday = createHoliday();
		HolidayTrackRequest request = createHolidayTrackRequest();
		HolidayCommand command = mock(HolidayCommand.class);
		when(holidayRepository.save(any())).thenReturn(holiday);
		when(commandFactory.createCommand(HolidayCommandFactory.TRACK, request)).thenReturn(command);
		applicationServiceImpl.trackHoliday(request);
		verify(holidayInvoker).invoke(command);
	}
	
	@DisplayName("trackHoliday: should return HolidayTrackResponse")
	@Test
	void trackHolidayShouldReturnHolidayResponse() {
		HolidayTrackRequest request = createHolidayTrackRequest();
		HolidayTrackResponse expectedResponse = createTrackResponse();
		when(holidayInvoker.invoke(any())).thenReturn(createTrackResponse());
		HolidayTrackResponse response = applicationServiceImpl.trackHoliday(request);
		assertEquals(expectedResponse.getHolidayTrackingId(), response.getHolidayTrackingId());
	}
	
	private HolidayTrackRequest createHolidayTrackRequest() {
		return HolidayTrackRequest.builder()
				.holidayTrackingId(UUID.nameUUIDFromBytes(HOLIDAY_TRACKING_ID_STR.getBytes()))
				.build();
	}

	private HolidayCreateResponse createCreateResponse() {
		return HolidayCreateResponse.builder()
				.holidayTrackingId(UUID.nameUUIDFromBytes(HOLIDAY_TRACKING_ID_STR.getBytes()))
				.build();
	}
	
	private HolidayTrackResponse createTrackResponse() {
		return HolidayTrackResponse.builder()
				.holidayTrackingId(UUID.nameUUIDFromBytes(HOLIDAY_TRACKING_ID_STR.getBytes()))
				.build();
	}

	private Holiday createHoliday() {
		return Holiday.builder()
				.withHolidayId(new HolidayId(UUID.nameUUIDFromBytes("value".getBytes())))
				.withTrackingId(new TrackingId(UUID.nameUUIDFromBytes(HOLIDAY_TRACKING_ID_STR.getBytes())))
				.build();
	}

	private HolidayCreateRequest createHolidayRequest() {
		return HolidayCreateRequest.builder()
				.people(List.of(Person.builder()
						.holidayId(UUID.nameUUIDFromBytes("value".getBytes()))
						.name("personName")
						.build()))
				.payments(List.of(Payment.builder()
						.description("description")
						.price(new Money(new BigDecimal(10)))
						.person(Person
								.builder()
								.holidayId(UUID.nameUUIDFromBytes("value".getBytes()))
								.name("name")
								.build())
						.build()))
				.build();
	}

}
