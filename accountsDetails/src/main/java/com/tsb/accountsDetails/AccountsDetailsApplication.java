package com.tsb.accountsDetails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.tsb.accountsDetails"})
public class AccountsDetailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsDetailsApplication.class, args);
	}

}
