package com.estockmarket.command;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class,org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class}
)
@EnableSwagger2
public class EstockmarketCommandApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstockmarketCommandApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}


}
