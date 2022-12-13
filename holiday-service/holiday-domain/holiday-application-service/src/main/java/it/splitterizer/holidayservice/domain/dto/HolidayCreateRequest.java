package it.splitterizer.holidayservice.domain.dto;

import java.util.List;
import java.util.Set;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class HolidayCreateRequest implements Request{
	@NotNull
	private final Set<Person> people;
	@NotNull
	private final List<Payment> payments;
}
