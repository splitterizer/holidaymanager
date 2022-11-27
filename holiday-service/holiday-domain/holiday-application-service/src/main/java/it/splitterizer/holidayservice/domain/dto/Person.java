package it.splitterizer.holidayservice.domain.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Person {
	@NotNull
	private UUID holidayId;
	@NotNull
	private String name;
}
