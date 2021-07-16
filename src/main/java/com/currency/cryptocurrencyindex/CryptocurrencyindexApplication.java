package com.currency.cryptocurrencyindex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CryptocurrencyindexApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptocurrencyindexApplication.class, args);
	}

}
