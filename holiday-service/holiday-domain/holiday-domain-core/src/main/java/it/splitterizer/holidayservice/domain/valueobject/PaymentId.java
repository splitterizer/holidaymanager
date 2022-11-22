package it.splitterizer.holidayservice.domain.valueobject;

import java.util.UUID;

import it.splitter.domain.valueobject.BaseId;

public class PaymentId extends BaseId<UUID>{

	public PaymentId(UUID uuid) {
		super(uuid);
	}
}
