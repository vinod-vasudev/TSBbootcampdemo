package com.ob.tsb.transactions;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.ob.tsb")
public class TransactionsApplication {
    public static void main(String[] args){
        SpringApplication.run(
                TransactionsApplication.class, args);
    }
}
