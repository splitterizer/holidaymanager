package it.splitterizer.holidayservice.domain.dto;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class HolidayTrackResponse {
	@NotNull
	private final UUID holidayTrackingId;
	@NotNull
	private List<Person> people;
	private List<String> failureMessages;
}
