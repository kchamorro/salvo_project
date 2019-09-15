package com.webcode.kc.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(PlayerRepository repository) {
		return (args) -> {
			// save a players
			repository.save(new Player(1, "Jack", "Bauer", "j.bauer@ctu.gov"));
			repository.save(new Player(2, "Chloe", "O'Brian", "c.obrian@ctu.gov"));
			repository.save(new Player(3, "Kim", "Bauer","kim_bauer@gmail.com"));
			repository.save(new Player(4, "Tony", "Almeida", "t.almeida@ctu.gov"));
		};
	}
}