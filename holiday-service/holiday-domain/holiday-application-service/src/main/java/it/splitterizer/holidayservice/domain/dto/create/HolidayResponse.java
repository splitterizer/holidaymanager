package it.splitterizer.holidayservice.domain.dto.create;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class HolidayResponse {
	@NotNull
	private final UUID holidayTrackingId;
	@NotNull
	private final String message;
}
