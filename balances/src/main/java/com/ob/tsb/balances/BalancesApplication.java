package com.ob.tsb.balances;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.ob.tsb")
public class BalancesApplication {
    public static void main(String[] args){
        SpringApplication.run(
                BalancesApplication.class, args);
    }
}
