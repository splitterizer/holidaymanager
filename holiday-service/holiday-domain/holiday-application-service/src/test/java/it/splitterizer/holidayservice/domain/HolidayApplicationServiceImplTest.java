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
import it.splitterizer.holidayservice.domain.dto.create.HolidayRequest;
import it.splitterizer.holidayservice.domain.dto.create.HolidayResponse;
import it.splitterizer.holidayservice.domain.dto.create.Payment;
import it.splitterizer.holidayservice.domain.dto.create.Person;
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

	@DisplayName("create command: on create holiday call create command once")
	@Test
	void createCmdshouldCallCreateCommandOnce() {
		Holiday holiday = createHoliday();
		HolidayRequest holidayRequest = createHolidayRequest();
		HolidayCommand command = mock(HolidayCommand.class);
		when(holidayRepository.save(any())).thenReturn(holiday);
		when(commandFactory.createCommand(HolidayCommandFactory.CREATE, holidayRequest)).thenReturn(command);
		applicationServiceImpl.createHoliday(holidayRequest);
		verify(commandFactory).createCommand(HolidayCommandFactory.CREATE, holidayRequest);
	}

	@DisplayName("create command: should call invoke once")
	@Test
	void createCmdshouldCallInvokeOnce() {
		Holiday holiday = createHoliday();
		HolidayRequest holidayRequest = createHolidayRequest();
		HolidayCommand command = mock(HolidayCommand.class);
		when(holidayRepository.save(any())).thenReturn(holiday);
		when(commandFactory.createCommand(HolidayCommandFactory.CREATE, holidayRequest)).thenReturn(command);
		applicationServiceImpl.createHoliday(holidayRequest);
		verify(holidayInvoker).invoke(command);
	}
	
	@DisplayName("create command: should return HolidayResponse")
	@Test
	void createCmdShouldReturnHolidayResponse() {
		HolidayRequest holidayRequest = createHolidayRequest();
		HolidayResponse expectedResponse = createResponse();
		when(holidayInvoker.invoke(any())).thenReturn(createResponse());
		HolidayResponse response = applicationServiceImpl.createHoliday(holidayRequest);
		assertEquals(expectedResponse.getHolidayTrackingId(), response.getHolidayTrackingId());
	}
	
	
	
	private HolidayResponse createResponse() {
		return HolidayResponse.builder()
				.holidayTrackingId(UUID.nameUUIDFromBytes(HOLIDAY_TRACKING_ID_STR.getBytes()))
				.build();
	}

	private Holiday createHoliday() {
		return Holiday.builder()
				.withHolidayId(new HolidayId(UUID.nameUUIDFromBytes("value".getBytes())))
				.withTrackingId(new TrackingId(UUID.nameUUIDFromBytes(HOLIDAY_TRACKING_ID_STR.getBytes())))
				.build();
	}

	private HolidayRequest createHolidayRequest() {
		return HolidayRequest.builder()
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
