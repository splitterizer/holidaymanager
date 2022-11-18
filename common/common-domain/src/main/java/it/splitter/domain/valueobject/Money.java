package it.splitter.domain.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record Money(BigDecimal amount) {
	
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
	
	private BigDecimal setScale(BigDecimal input) {
		return input.setScale(2, RoundingMode.HALF_EVEN);
	}
}
