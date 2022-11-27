package it.splitterizer.holidayservice.domain.dto.track;

import java.util.List;
import java.util.UUID;

import it.splitterizer.holidayservice.domain.dto.create.Person;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class TrackHolidayResponse {
	@NotNull
	private final UUID holidayTrackingId;
	@NotNull
	private List<Person> people;
	private List<String> failureMessages;
}
