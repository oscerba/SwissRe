package com.oscerba.swissre.converter;

import com.oscerba.swissre.pojo.CryptoCurrency;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class representing converter to EUR
 */
public class WebServiceValueConverter extends ValueConverter {

	private final Pattern responsePattern = Pattern.compile("\\{\"EUR\":(\\d*(.\\d*)?)}");

	/**
	 * Converts value to EUR
	 *
	 * @param cryptoCurrency crypto currency to be converted
	 * @return price
	 */
	@Override
	public BigDecimal convert(CryptoCurrency cryptoCurrency) {
		BigDecimal price = BigDecimal.ZERO;
		try {
			URL url = new URL(String.format("https://min-api.cryptocompare.com/data/price?fsym=%s&tsyms=EUR", cryptoCurrency.getCryptoSymbol()));//your url i.e fetch data from .
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Request failed. Code: " + conn.getResponseCode());
			}

			InputStreamReader in = new InputStreamReader(conn.getInputStream());
			BufferedReader br = new BufferedReader(in);
			String output;
			while ((output = br.readLine()) != null) {
				Matcher matcher = responsePattern.matcher(output);
				if (matcher.matches()) {
					price = new BigDecimal(matcher.group(1));
				} else {
					System.err.printf("Failed to retrieve price for crypto currency [%s]. It will be ignored.%n", cryptoCurrency);
				}
			}
			conn.disconnect();

		} catch (Exception e) {
			System.err.printf("Exception occurred, while retrieving price for crypto currency [%s]. Reason: %s%n", cryptoCurrency, e.getMessage());
		}
		return price;
	}

}
