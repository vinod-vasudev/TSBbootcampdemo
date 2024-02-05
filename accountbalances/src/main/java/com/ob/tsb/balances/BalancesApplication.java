package com.ob.tsb.balances;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.ob.tsb.balances"})
public class BalancesApplication {
    public static void main(String[] args){
        SpringApplication.run(
                BalancesApplication.class, args);
    }
}
