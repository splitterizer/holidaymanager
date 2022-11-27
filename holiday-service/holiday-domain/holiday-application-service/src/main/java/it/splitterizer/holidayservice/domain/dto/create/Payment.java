package it.splitterizer.holidayservice.domain.dto.create;

import it.splitter.domain.valueobject.Money;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Payment {

	@NotNull
	private final Person person;
	@NotNull
	private final String description;
	@NotNull
	private final Money price;
}
