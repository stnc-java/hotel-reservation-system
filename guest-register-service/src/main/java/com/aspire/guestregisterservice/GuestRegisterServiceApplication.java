package com.aspire.guestregisterservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GuestRegisterServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuestRegisterServiceApplication.class, args);
	}

}
