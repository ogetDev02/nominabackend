package com.oget.ogetpro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.oget.ogetpro"})
public class OgetBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(OgetBackApplication.class, args);
	}

}
