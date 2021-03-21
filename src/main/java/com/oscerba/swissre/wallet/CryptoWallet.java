package com.oscerba.swissre.wallet;

import com.oscerba.swissre.pojo.CryptoCurrency;
import com.oscerba.swissre.pojo.CryptoCurrencyWalletEntry;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Abstract class representing Wallet for cryptos
 */
public abstract class CryptoWallet {

	/**
	 * Add crypto currency to wallet
	 *
	 * @param cryptoCurrency crypto currency to be added
	 */
	public abstract void addCryptoCurrency(CryptoCurrency cryptoCurrency);

	/**
	 * Returns map of all crypto currencies
	 *
	 * @return map of all crypto currencies where key is crypto symbol and value crypto currency
	 */
	public abstract Map<String, CryptoCurrencyWalletEntry> getWalletEntries();

	/**
	 * Returns total value of all crypto currencies stored in wallet
	 *
	 * @return total value of all crypto currencies stored in wallet
	 */
	public abstract BigDecimal getTotalValue();
}
