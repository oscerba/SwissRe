package com.oscerba.swissre.pojo;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Class representing entry in crypto wallet
 */
public class CryptoCurrencyWalletEntry {

	private final String cryptoSymbol;
	private final BigDecimal quantity;
	private final BigDecimal price;

	public CryptoCurrencyWalletEntry(String cryptoSymbol, BigDecimal quantity, BigDecimal price) {
		this.cryptoSymbol = cryptoSymbol;
		this.quantity = quantity;
		this.price = price;
	}

	public String getCryptoSymbol() {
		return cryptoSymbol;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public BigDecimal getValue() {
		return quantity.multiply(price);
	}

	@Override
	public String toString() {
		return "CryptoCurrencyWalletEntry{" +
						"cryptoSymbol='" + cryptoSymbol + '\'' +
						", quantity=" + quantity +
						", price=" + price +
						", value=" + getValue() +
						'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CryptoCurrencyWalletEntry that = (CryptoCurrencyWalletEntry) o;
		return Objects.equals(cryptoSymbol, that.cryptoSymbol) && Objects.equals(quantity, that.quantity) && Objects.equals(price, that.price);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cryptoSymbol, quantity, price);
	}
}
