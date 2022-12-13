package it.splitterizer.holidayservice.domain.policy;

import java.util.List;

import it.splitterizer.holidayservice.domain.entity.Holiday;
import it.splitterizer.holidayservice.domain.valueobject.AccountingResultPerson;

public abstract class HolidayAccountingPolicy {

	protected final Holiday holiday;
	
	public HolidayAccountingPolicy(Holiday holiday) {
		this.holiday = holiday;
	}
	
	
	public abstract List<AccountingResultPerson> split();
	
}
