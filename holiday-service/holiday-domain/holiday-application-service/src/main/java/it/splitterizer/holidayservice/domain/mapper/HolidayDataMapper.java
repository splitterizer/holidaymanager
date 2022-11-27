package it.splitterizer.holidayservice.domain.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import it.splitter.domain.valueobject.HolidayId;
import it.splitterizer.holidayservice.domain.dto.create.HolidayRequest;
import it.splitterizer.holidayservice.domain.dto.create.HolidayResponse;
import it.splitterizer.holidayservice.domain.entity.Holiday;
import it.splitterizer.holidayservice.domain.entity.Payment;
import it.splitterizer.holidayservice.domain.entity.Person;

@Component
public class HolidayDataMapper {

	public Holiday toHoliday(HolidayRequest holidayRequest) {
		return Holiday.builder()
				.withPeople(toListOfPerson(holidayRequest.getPeople()))
				.withPayments(toListOfPayment(holidayRequest.getPayments()))
				.build();
	}
	
	public HolidayResponse toHolidayResponse(Holiday holiday) {
		return HolidayResponse.builder()
					.holidayTrackingId(holiday.getTrackingId().getValue())
					.build();
	}

	private List<Payment> toListOfPayment(
			List<it.splitterizer.holidayservice.domain.dto.create.Payment> paymentsDTO) {
		return paymentsDTO.stream()
				.map(p -> Payment.builder()
						.withPerson(toPerson(p.getPerson()))
						.withDescription(p.getDescription())
						.withPrice(p.getPrice())
						.build())
				.collect(Collectors.toList());
	}

	private List<Person> toListOfPerson(
			List<it.splitterizer.holidayservice.domain.dto.create.Person> peopleDTO) {
		return peopleDTO.stream().map(this::toPerson)
				.collect(Collectors.toList());
	}
	
	private Person toPerson(
			it.splitterizer.holidayservice.domain.dto.create.Person personDTO) {
		return Person.builder()
				.withHolidayId(new HolidayId(personDTO.getHolidayId()))
				.withName(personDTO.getName())
				.build();
	}
}
