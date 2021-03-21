package com.oscerba.swissre.reader;

import com.oscerba.swissre.pojo.CryptoCurrency;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * Class representing input of all crypto currencies
 */
public abstract class CryptoInputReader {
	/**
	 * Method, which loads crypto currencies
	 *
	 * @return stream of crypto currencies
	 */
	public abstract Stream<CryptoCurrency> loadCryptoCurrencies() throws IOException;
}
