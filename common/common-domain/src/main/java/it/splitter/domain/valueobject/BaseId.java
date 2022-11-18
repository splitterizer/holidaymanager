package it.splitter.domain.valueobject;

import java.util.Objects;

public abstract class BaseId<T> {

	private T value;

	protected BaseId(T value) {
		super();
		this.value = value;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof BaseId))
			return false;
		BaseId<?> other = (BaseId<?>) obj;
		return Objects.equals(value, other.value);
	}
	
	
}
