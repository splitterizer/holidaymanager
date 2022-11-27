package it.splitterizer.holidayservice.domain.dto.track;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class TrackHolidayQuery {
	@NotNull
	private final UUID holidayTrackingId;
}
