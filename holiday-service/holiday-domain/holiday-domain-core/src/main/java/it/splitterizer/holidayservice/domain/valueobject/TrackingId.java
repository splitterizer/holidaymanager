package it.splitterizer.holidayservice.domain.valueobject;

import java.util.UUID;

import it.splitter.domain.valueobject.BaseId;

public class TrackingId extends BaseId<UUID>{

	public TrackingId(UUID uuid) {
		super(uuid);
	}
}
