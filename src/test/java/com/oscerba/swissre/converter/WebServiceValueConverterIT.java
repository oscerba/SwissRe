package com.oscerba.swissre.converter;

import com.oscerba.swissre.pojo.CryptoCurrency;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class WebServiceValueConverterIT {

	@Test
	public void testPriceIsNotZeroForBtc() {
		BigDecimal price = new WebServiceValueConverter().convert(new CryptoCurrency("BTC", new BigDecimal(5)));
		assertNotEquals(BigDecimal.ZERO, price);
	}

	@Test
	public void testPriceIsZeroForNonExistingCrypto() {
		BigDecimal price = new WebServiceValueConverter().convert(new CryptoCurrency("NON-EXISTING", new BigDecimal(5)));
		assertEquals(BigDecimal.ZERO, price);
	}
}
