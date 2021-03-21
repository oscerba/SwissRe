package com.oscerba.swissre.reader;

import com.oscerba.swissre.pojo.CryptoCurrency;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileCryptoInputReaderReaderTest {

	@Test
	public void testReadInputFile() throws URISyntaxException, IOException {
		List<CryptoCurrency> collect = new FileCryptoInputReader(ClassLoader.getSystemResource("test.txt").toURI()).loadCryptoCurrencies().collect(Collectors.toList());
		assertEquals(collect, Arrays.asList(new CryptoCurrency("BTC", new BigDecimal("5")), new CryptoCurrency("NE", new BigDecimal(10))));
	}
}
