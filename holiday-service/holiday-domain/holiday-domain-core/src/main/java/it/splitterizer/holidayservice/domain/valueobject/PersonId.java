package it.splitterizer.holidayservice.domain.valueobject;

import java.util.UUID;

import it.splitter.domain.valueobject.BaseId;

public class PersonId extends BaseId<UUID> {

	public PersonId(UUID uuid) {
		super(uuid);
	}
}
