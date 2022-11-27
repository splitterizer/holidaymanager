package it.splitterizer.holidayservice.domain.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class HolidayTrackRequest implements Request {
	@NotNull
	private UUID holidayTrackingId;
}
