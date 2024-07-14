package com.hamy.tradeshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The TradeShopApplication class is the entry point of the TradeShop Spring Boot application.
 * It contains the main method which launches the application.
 */
@SpringBootApplication(scanBasePackages = "com.hamy.tradeshop")
public class TradeShopApplication {

    /**
     * The main method which serves as the entry point for the Spring Boot application.
     * 
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(TradeShopApplication.class, args);
    }
}
