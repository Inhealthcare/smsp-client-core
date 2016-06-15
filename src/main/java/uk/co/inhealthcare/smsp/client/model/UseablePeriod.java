package uk.co.inhealthcare.smsp.client.model;

import java.math.BigInteger;

public class UseablePeriod {

	public enum Type {
		Low, High;
	}

	private Type type;
	private BigInteger value;

	public UseablePeriod(Type type, BigInteger value) {
		this.type = type;
		this.value = value;
	}

	public Type getType() {
		return type;
	}

	public BigInteger getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "UseablePeriod [type=" + type + ", value=" + value + "]";
	}

}
