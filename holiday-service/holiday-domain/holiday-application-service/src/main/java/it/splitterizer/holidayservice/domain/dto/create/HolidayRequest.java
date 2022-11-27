package it.splitterizer.holidayservice.domain.dto.create;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class HolidayRequest implements Request{
	@NotNull
	private final List<Person> people;
	@NotNull
	private final List<Payment> payments;
}
