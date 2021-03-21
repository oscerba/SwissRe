package com.oscerba.swissre.wallet;

import com.oscerba.swissre.converter.ValueConverter;
import com.oscerba.swissre.pojo.CryptoCurrency;
import com.oscerba.swissre.pojo.CryptoCurrencyWalletEntry;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Class representing in memory store of cryptos
 */
public class InMemoryCryptoWallet extends CryptoWallet {

	private final Map<String, CryptoCurrencyWalletEntry> walletEntries = new HashMap<>();
	private final ValueConverter valueConverter;
	private BigDecimal totalValue = BigDecimal.ZERO;

	public InMemoryCryptoWallet(ValueConverter valueConverter) {
		this.valueConverter = valueConverter;
	}

	@Override
	public void addCryptoCurrency(CryptoCurrency cryptoCurrency) {
		CryptoCurrencyWalletEntry cryptoCurrencyWalletEntry;
		String cryptoSymbol = cryptoCurrency.getCryptoSymbol();
		BigDecimal price = valueConverter.convert(cryptoCurrency);
		BigDecimal quantity = cryptoCurrency.getQuantity();
		if (walletEntries.containsKey(cryptoSymbol)) {
			BigDecimal totalQuantity = walletEntries.get(cryptoSymbol).getQuantity().add(quantity);
			cryptoCurrencyWalletEntry = new CryptoCurrencyWalletEntry(cryptoSymbol, totalQuantity, price);
		} else {
			cryptoCurrencyWalletEntry = new CryptoCurrencyWalletEntry(cryptoSymbol, quantity, price);
		}
		totalValue = totalValue.add(cryptoCurrencyWalletEntry.getValue());
		walletEntries.put(cryptoSymbol, cryptoCurrencyWalletEntry);
	}

	@Override
	public Map<String, CryptoCurrencyWalletEntry> getWalletEntries() {
		return walletEntries;
	}

	@Override
	public BigDecimal getTotalValue() {
		return totalValue;
	}
}
