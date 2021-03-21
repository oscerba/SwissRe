package com.oscerba.swissre.reader;

import com.oscerba.swissre.pojo.CryptoCurrency;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Class representing file input for crypto currencies
 */
public class FileCryptoInputReader extends CryptoInputReader {

	private final URI uri;

	/**
	 * Constructor, which takes URI of file as parameter
	 *
	 * @param uri URI of the input file
	 */
	public FileCryptoInputReader(URI uri) {
		this.uri = uri;
	}

	/**
	 * Method, which loads crypto currencies
	 *
	 * @return stream of crypto currencies
	 */
	@Override
	public Stream<CryptoCurrency> loadCryptoCurrencies() throws IOException {
		Stream<CryptoCurrency> cryptoCurrencyStream;

		Stream<String> lines = Files.lines(Paths.get(uri));
		cryptoCurrencyStream = lines.map(x -> {
			String[] split = x.split("=");
			return new CryptoCurrency(split[0], new BigDecimal(split[1]));
		});
		return cryptoCurrencyStream;
	}
}
