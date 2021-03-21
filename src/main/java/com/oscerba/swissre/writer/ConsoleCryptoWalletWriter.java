package com.oscerba.swissre.writer;

import com.oscerba.swissre.wallet.CryptoWallet;

/**
 * Class representing writer which will write info about currently hold crypto currencies and their prices
 */
public class ConsoleCryptoWalletWriter extends CryptoWalletWriter {

	private final CryptoWallet cryptoWallet;

	public ConsoleCryptoWalletWriter(CryptoWallet cryptoWallet) {
		this.cryptoWallet = cryptoWallet;
	}

	@Override
	public void write() {
		cryptoWallet.getWalletEntries().values().forEach(System.out::println);
		System.out.printf("Total Value: %f%n", cryptoWallet.getTotalValue());
	}
}
