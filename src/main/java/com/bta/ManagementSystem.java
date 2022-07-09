package com.bta;

import com.bta.email.EmailService;
import com.bta.repository.CustomerOrderRepository;
import com.bta.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication

	(exclude = {
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class}
)
public class ManagementSystem {


	public static void main(String[] args) {
		SpringApplication.run(ManagementSystem.class, args);
	}

	}


