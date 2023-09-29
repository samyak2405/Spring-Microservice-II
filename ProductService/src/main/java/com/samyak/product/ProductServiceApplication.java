package com.samyak.product;

import jakarta.persistence.Entity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableJpaRepositories("com.samyak.repository")
@EntityScan("com.samyak.entity")
@ComponentScan("com.samyak.*")
@Configuration
@AutoConfiguration
@EnableWebMvc
@Slf4j
@SpringBootApplication
public class ProductServiceApplication {

	public static void main(String[] args) {
		log.info("Inside main method");
		SpringApplication.run(ProductServiceApplication.class, args);
	}

}
