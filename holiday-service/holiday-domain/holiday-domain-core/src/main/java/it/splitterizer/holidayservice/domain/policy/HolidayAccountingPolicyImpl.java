package it.splitterizer.holidayservice.domain.policy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import it.splitter.domain.valueobject.Money;
import it.splitterizer.holidayservice.domain.entity.Holiday;
import it.splitterizer.holidayservice.domain.entity.Payment;
import it.splitterizer.holidayservice.domain.valueobject.AccountingResultPerson;
import it.splitterizer.holidayservice.domain.valueobject.Person;

public class HolidayAccountingPolicyImpl extends HolidayAccountingPolicy {

	public HolidayAccountingPolicyImpl(Holiday holiday) {
		super(holiday);
	}

	@Override
	public List<AccountingResultPerson> split() {
		Map<Person, BigDecimal> toBePaidPerPerson = holiday.getPayments().stream()
			.collect(paidPerPerson())
			.entrySet().stream()
			.map(this::toBePaid)
			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		
		Map<Person, BigDecimal> dueToCreditors = toBePaidPerPerson.entrySet().stream()
				.filter(e -> e.getValue().compareTo(BigDecimal.ZERO) < 0)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		
		BigDecimal totalDueToCreditors = dueToCreditors.entrySet().stream()
				.collect(Collectors.reducing(BigDecimal.ZERO, Map.Entry::getValue, BigDecimal::add))
				.abs();
		
		Map<Person, BigDecimal> percentageDueToCreditors = dueToCreditors.entrySet().stream()
				.map(e -> calculatePercentage(e, totalDueToCreditors))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		
		List<AccountingResultPerson> listDebtors = toBePaidPerPerson.entrySet().stream()
				.filter(this::debtorsFilter)
				.flatMap(e -> toAccountingResultPerson(e, percentageDueToCreditors))
				.collect(Collectors.toList());
		
		return listDebtors;
	}
	
	
	private Map.Entry<Person, BigDecimal> calculatePercentage(Map.Entry<Person, BigDecimal> entry, BigDecimal total) {
		return Map.entry(entry.getKey(), entry.getValue().abs().divide(total, 3, RoundingMode.HALF_EVEN));
	}
	
	private Collector<Payment, ?, Map<Person, Money>> paidPerPerson() {
		return Collectors.groupingBy(
				Payment::getPerson, 
				Collectors.reducing(Money.ZERO, Payment::getPrice, Money::add));
	}
	
	private Map.Entry<Person, BigDecimal>  toBePaid(Map.Entry<Person, Money> entry) {
		return Map.entry(entry.getKey(), 
				holiday.totalPerPerson().subtract(entry.getValue()).amount());
	}

	private Map.Entry<Person, Money> totalToBeReturned(Person person, Money paid, Money totalPerPerson) {
		return Map.entry(person, totalPerPerson.subtract(paid));
	}
	
	private boolean debtorsFilter(Map.Entry<Person, BigDecimal> entry) {
		return entry.getValue().compareTo(BigDecimal.ZERO) > 0;
	}
	
	private Stream<AccountingResultPerson> toAccountingResultPerson(Map.Entry<Person, BigDecimal> debtor, Map<Person, BigDecimal> percentageDueToCreditors) {
		return percentageDueToCreditors.entrySet().stream()
				.map(creditor -> Map.entry(creditor.getKey(), creditor.getValue().multiply(debtor.getValue())))
				.map(creditor -> new AccountingResultPerson(debtor.getKey(), creditor.getKey(), new Money(creditor.getValue())));
	}
}
