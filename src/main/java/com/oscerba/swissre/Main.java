package com.oscerba.swissre;

import com.oscerba.swissre.converter.ValueConverter;
import com.oscerba.swissre.converter.WebServiceValueConverter;
import com.oscerba.swissre.reader.CryptoInputReader;
import com.oscerba.swissre.reader.FileCryptoInputReader;
import com.oscerba.swissre.wallet.CryptoWallet;
import com.oscerba.swissre.wallet.InMemoryCryptoWallet;
import com.oscerba.swissre.writer.ConsoleCryptoWalletWriter;

import java.io.IOException;
import java.nio.file.Paths;

public class Main {

	public static final String FILE_NAME = "bobs_crypto.txt";

	public static void main(String[] args) {

		CryptoInputReader cryptoInputReader = new FileCryptoInputReader(Paths.get(FILE_NAME).toUri());
		ValueConverter valueConverter = new WebServiceValueConverter();
		CryptoWallet cryptoWallet = new InMemoryCryptoWallet(valueConverter);
		try {
			cryptoInputReader.loadCryptoCurrencies().forEach(cryptoWallet::addCryptoCurrency);
			new ConsoleCryptoWalletWriter(cryptoWallet).write();
		} catch (IOException e) {
			System.err.printf("Error during reading file. Reason: [%s]%n", e.getMessage());
		}
	}
}

