package com.oscerba.swissre.pojo;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Class representing input
 */
public class CryptoCurrency {

	private final String cryptoSymbol;
	private final BigDecimal quantity;

	public CryptoCurrency(String cryptoSymbol, BigDecimal quantity) {
		this.cryptoSymbol = cryptoSymbol;
		this.quantity = quantity;
	}

	public String getCryptoSymbol() {
		return cryptoSymbol;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	@Override
	public String toString() {
		return "CryptoCurrency{" +
						"cryptoSymbol='" + cryptoSymbol + '\'' +
						", quantity=" + quantity +
						'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CryptoCurrency that = (CryptoCurrency) o;
		return Objects.equals(cryptoSymbol, that.cryptoSymbol) && Objects.equals(quantity, that.quantity);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cryptoSymbol, quantity);
	}
}
