package it.splitter.domain.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record Money(BigDecimal amount) {
	
	public static final Money ZERO = new Money(BigDecimal.ZERO);
	
	public static Money make(double value) {
		if (value < 0F) {
			throw new IllegalArgumentException("money cannot have negative value");
		}
		
		return new Money(setScale(new BigDecimal(value)));
	}
	
	public Money(BigDecimal amount) {
		this.amount = setScale(amount);
	}
	
	public boolean isGreaterThanZero() {
		return amount != null && amount.compareTo(BigDecimal.ZERO) > 0;
	}
	
	public boolean isGreaterThan(Money money) {
		return amount != null && amount.compareTo(money.amount()) > 0;
	}
	
	public Money add(Money money) {
		validateAmount();
		return new Money(setScale(amount.add(money.amount())));
	}
	
	public Money subtract(Money money) {
		validateAmount();
		return new Money(setScale(amount.subtract(money.amount())));
	}
	
	public Money multiply(Money money) {
		validateAmount();
		return new Money(setScale(amount.multiply(money.amount())));
	}
	
	private void validateAmount() {
		if (amount == null) {
			throw new IllegalStateException("unable to add money because amount is null");
		}
	}
	
	private static BigDecimal setScale(BigDecimal input) {
		return input.setScale(2, RoundingMode.HALF_EVEN);
	}
}
