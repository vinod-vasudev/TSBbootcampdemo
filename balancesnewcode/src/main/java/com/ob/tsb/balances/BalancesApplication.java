package com.ob.tsb.balances;


import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.ob.tsb.balances"})
@SecurityScheme(name="jwt", scheme="bearer", type=SecuritySchemeType.HTTP, in=SecuritySchemeIn.HEADER)
public class BalancesApplication {
    public static void main(String[] args){
        SpringApplication.run(
                BalancesApplication.class, args);
    }
}
