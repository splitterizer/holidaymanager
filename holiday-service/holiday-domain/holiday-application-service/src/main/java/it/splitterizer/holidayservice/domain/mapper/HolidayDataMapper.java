package it.splitterizer.holidayservice.domain.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import it.splitter.domain.valueobject.HolidayId;
import it.splitterizer.holidayservice.domain.dto.HolidayCreateRequest;
import it.splitterizer.holidayservice.domain.dto.HolidayCreateResponse;
import it.splitterizer.holidayservice.domain.dto.HolidayTrackResponse;
import it.splitterizer.holidayservice.domain.entity.Holiday;
import it.splitterizer.holidayservice.domain.entity.Payment;
import it.splitterizer.holidayservice.domain.entity.Person;

@Component
public class HolidayDataMapper {

	public Holiday toHoliday(HolidayCreateRequest holidayRequest) {
		return Holiday.builder()
				.withPeople(toListOfPerson(holidayRequest.getPeople()))
				.withPayments(toListOfPayment(holidayRequest.getPayments()))
				.build();
	}
	
	public HolidayCreateResponse toHolidayResponse(Holiday holiday) {
		return HolidayCreateResponse.builder()
					.holidayTrackingId(holiday.getTrackingId().getValue())
					.build();
	}
	
	public HolidayTrackResponse toHolidayTrackResponse(Holiday holiday) {
		return HolidayTrackResponse.builder()
				.holidayTrackingId(holiday.getTrackingId().getValue())
				.people(toListOfPersonDTO(holiday.getPeople()))
				.build();
	}

	private List<Payment> toListOfPayment(
			List<it.splitterizer.holidayservice.domain.dto.Payment> paymentsDTO) {
		return paymentsDTO.stream()
				.map(p -> Payment.builder()
						.withPerson(toPerson(p.getPerson()))
						.withDescription(p.getDescription())
						.withPrice(p.getPrice())
						.build())
				.collect(Collectors.toList());
	}

	private List<Person> toListOfPerson(
			List<it.splitterizer.holidayservice.domain.dto.Person> peopleDTO) {
		return peopleDTO.stream().map(this::toPerson)
				.collect(Collectors.toList());
	}
	
	private Person toPerson(
			it.splitterizer.holidayservice.domain.dto.Person personDTO) {
		return Person.builder()
				.withHolidayId(new HolidayId(personDTO.getHolidayId()))
				.withName(personDTO.getName())
				.build();
	}

	private List<it.splitterizer.holidayservice.domain.dto.Person> toListOfPersonDTO(List<Person> people) {
		return people.stream()
				.map(p -> it.splitterizer.holidayservice.domain.dto.Person.builder()
						.holidayId(p.getHolidayId().getValue())
						.name(p.getName())
						.build())
				.collect(Collectors.toList());
	}
}
