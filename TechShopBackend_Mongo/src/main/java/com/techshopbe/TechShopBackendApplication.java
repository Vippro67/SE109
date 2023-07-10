package com.techshopbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TechShopBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechShopBackendApplication.class, args);
	}

}
