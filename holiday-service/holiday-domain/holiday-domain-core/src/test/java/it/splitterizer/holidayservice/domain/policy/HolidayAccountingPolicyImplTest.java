package it.splitterizer.holidayservice.domain.policy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import it.splitter.domain.valueobject.Money;
import it.splitterizer.holidayservice.domain.entity.Holiday;
import it.splitterizer.holidayservice.domain.entity.Payment;
import it.splitterizer.holidayservice.domain.valueobject.AccountingResultPerson;
import it.splitterizer.holidayservice.domain.valueobject.PaymentId;
import it.splitterizer.holidayservice.domain.valueobject.Person;

public class HolidayAccountingPolicyImplTest {

	private static HolidayAccountingPolicyImpl holidayAccountingPolicyImpl;
	
	private final static Person ALBERTO = new Person("Alberto");
	private final static Person MARA = new Person("Mara");
	private final static Person FABIO = new Person("Fabio");
	private final static Person LUCA = new Person("Luca");
	private final static Person ANNA = new Person("Anna");
	
	@BeforeAll
	static void setupAll() {
		Holiday holiday = Holiday.builder()
				.withPayments(createListOfPayments())
				.withPeople(createListOfPeople())
				.build();
		holidayAccountingPolicyImpl = new HolidayAccountingPolicyImpl(holiday);
	}
	
	@DisplayName("should return the correct list of split payments")
	@Test
	void shouldReturnSplitList() {
		var expectedResult = List.of(
					new AccountingResultPerson(ALBERTO, MARA, Money.make(2.76)),
					new AccountingResultPerson(ALBERTO, FABIO, Money.make(0.34)),
					new AccountingResultPerson(ALBERTO, LUCA, Money.make(6.90)),
					new AccountingResultPerson(ANNA, MARA, Money.make(5.24)),
					new AccountingResultPerson(ANNA, FABIO, Money.make(0.65)),
					new AccountingResultPerson(ANNA, LUCA, Money.make(13.11))
				);
		var result = holidayAccountingPolicyImpl.split();
		
		assertAll(
					() -> assertTrue(result.contains(expectedResult.get(0)), "contains accounting Alberto/Mara"),
					() -> assertTrue(result.contains(expectedResult.get(1)), "contains accounting Alberto/Fabio"),
					() -> assertTrue(result.contains(expectedResult.get(2)), "contains accounting Alberto/Luca"),
					() -> assertTrue(result.contains(expectedResult.get(3)), "contains accounting Anna/Mara"),
					() -> assertTrue(result.contains(expectedResult.get(4)), "contains accounting Anna/Fabio"),
					() -> assertTrue(result.contains(expectedResult.get(5)), "contains accounting Anna/Luca")
				);
	}
	
	private static Set<Person> createListOfPeople() {
		return Set.of(ALBERTO, MARA, FABIO, LUCA, ANNA);
	}

	private static List<Payment> createListOfPayments() {
		return List.of(
				createPayment(ALBERTO, randomDescription(), Money.make(16), 1),
				createPayment(ALBERTO, randomDescription(), Money.make(23), 2),
				createPayment(ALBERTO, randomDescription(), Money.make(21), 4),
				createPayment(MARA   , randomDescription(), Money.make(33), 3),
				createPayment(MARA   , randomDescription(), Money.make(45), 6),
				createPayment(FABIO  , randomDescription(), Money.make(11), 5),
				createPayment(FABIO  , randomDescription(), Money.make(60), 7),
				createPayment(LUCA   , randomDescription(), Money.make(90), 8),
				createPayment(ANNA   , randomDescription(), Money.make(37), 9),
				createPayment(ANNA   , randomDescription(), Money.make(14), 10)
			);
		
		// total = 350  totAlberto = 60  totMara = 78  totFabio = 71  totLuca = 90  totAnna = 51
	}
	
	private static Payment createPayment(Person person, String description, Money price, int uuid) {
		return Payment.builder()
				.withPaymentId(new PaymentId(uuid))
				.withDescription(description)
				.withPrice(price)
				.withPerson(person)
				.build();
	}
	
	private static String randomDescription() {
		return "random description" + new Random().nextInt();
	}

}
