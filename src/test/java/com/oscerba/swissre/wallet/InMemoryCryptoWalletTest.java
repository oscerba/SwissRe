package com.oscerba.swissre.wallet;

import com.oscerba.swissre.converter.ValueConverter;
import com.oscerba.swissre.pojo.CryptoCurrency;
import com.oscerba.swissre.pojo.CryptoCurrencyWalletEntry;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InMemoryCryptoWalletTest {

	private static ValueConverter valueConverter;

	@BeforeAll
	public static void init() {
		valueConverter = new ValueConverter() {
			@Override
			public BigDecimal convert(CryptoCurrency cryptoCurrency) {
				BigDecimal price = BigDecimal.ZERO;
				if (cryptoCurrency.getCryptoSymbol().equals("BTC")) {
					price = new BigDecimal("5");
				}
				return price;
			}
		};
	}

	@Test
	public void testTotalValueForExistingCrypto() {
		CryptoWallet cryptoWallet = new InMemoryCryptoWallet(valueConverter);
		cryptoWallet.addCryptoCurrency(new CryptoCurrency("BTC", new BigDecimal("5")));
		assertEquals(new BigDecimal("25"), cryptoWallet.getTotalValue());
	}

	@Test
	public void testWalletEntriesForExistingCrypto() {
		CryptoWallet cryptoWallet = new InMemoryCryptoWallet(valueConverter);
		cryptoWallet.addCryptoCurrency(new CryptoCurrency("BTC", new BigDecimal("5")));
		List<CryptoCurrencyWalletEntry> expected = Collections.singletonList(new CryptoCurrencyWalletEntry("BTC", new BigDecimal("5"), new BigDecimal("5")));
		Collection<CryptoCurrencyWalletEntry> actual = cryptoWallet.getWalletEntries().values();
		assertTrue(expected.containsAll(actual));
	}

	@Test
	public void testMultipleEntriesForExistingCrypto() {
		CryptoWallet cryptoWallet = new InMemoryCryptoWallet(valueConverter);
		cryptoWallet.addCryptoCurrency(new CryptoCurrency("BTC", new BigDecimal("5")));
		cryptoWallet.addCryptoCurrency(new CryptoCurrency("BTC", new BigDecimal("5")));
		List<CryptoCurrencyWalletEntry> expected = Collections.singletonList(new CryptoCurrencyWalletEntry("BTC", new BigDecimal("10"), new BigDecimal("5")));
		Collection<CryptoCurrencyWalletEntry> actual = cryptoWallet.getWalletEntries().values();
		assertTrue(expected.containsAll(actual));
	}

	@Test
	public void testAddNonExisting() {
		CryptoWallet cryptoWallet = new InMemoryCryptoWallet(valueConverter);
		cryptoWallet.addCryptoCurrency(new CryptoCurrency("NonExisting", new BigDecimal("5")));
		assertEquals(new BigDecimal("0"), cryptoWallet.getTotalValue());
	}
}
