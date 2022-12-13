package it.splitterizer.holidayservice.domain.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import it.splitterizer.holidayservice.domain.dto.HolidayCreateRequest;
import it.splitterizer.holidayservice.domain.dto.HolidayCreateResponse;
import it.splitterizer.holidayservice.domain.dto.HolidayTrackResponse;
import it.splitterizer.holidayservice.domain.entity.Holiday;
import it.splitterizer.holidayservice.domain.entity.Payment;
import it.splitterizer.holidayservice.domain.valueobject.Person;

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

	private Set<Person> toListOfPerson(
			Set<it.splitterizer.holidayservice.domain.dto.Person> peopleDTO) {
		return peopleDTO.stream().map(this::toPerson)
				.collect(Collectors.toSet());
	}
	
	private Person toPerson(
			it.splitterizer.holidayservice.domain.dto.Person personDTO) {
		return new Person(personDTO.getName());
	}

	private List<it.splitterizer.holidayservice.domain.dto.Person> toListOfPersonDTO(Set<Person> people) {
		return people.stream()
				.map(p -> it.splitterizer.holidayservice.domain.dto.Person.builder()
						.name(p.name())
						.build())
				.collect(Collectors.toList());
	}
}
