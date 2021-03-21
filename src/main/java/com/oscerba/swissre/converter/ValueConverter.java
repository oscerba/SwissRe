package com.oscerba.swissre.converter;

import com.oscerba.swissre.pojo.CryptoCurrency;

import java.math.BigDecimal;

public abstract class ValueConverter {

	public abstract BigDecimal convert(CryptoCurrency cryptoCurrency);
}
