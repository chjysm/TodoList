package com.api.wishList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class WishListApplication {

	public static void main(String[] args) {

		SpringApplication.run(WishListApplication.class, args);
	}

}
