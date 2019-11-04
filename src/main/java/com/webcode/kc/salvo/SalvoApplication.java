package com.webcode.kc.salvo;

import com.webcode.kc.salvo.model.*;
import com.webcode.kc.salvo.repository.GamePlayerRepository;
import com.webcode.kc.salvo.repository.GameRepository;
import com.webcode.kc.salvo.repository.PlayerRepository;
import com.webcode.kc.salvo.repository.ScoreRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Arrays;



/*
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
 */

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}


	@Bean
	public CommandLineRunner initData(
			PlayerRepository playerRepository
			, GameRepository gameRepository
			, GamePlayerRepository gamePlayerRepository
			, ScoreRepository scoreRepository
	) {
		return (args) -> {


			// save a Players
			Player jack = playerRepository.save(new Player("j.bauer@ctu.gov", "Jack", "Bauer"));
			Player chloe = playerRepository.save(new Player("c.obrian@ctu.gov", "Chloe", "O'Brian"));
			Player kim = playerRepository.save(new Player("kim_bauer@gmail.com", "Kim", "Bauer"));
			Player tony = playerRepository.save(new Player("t.almeida@ctu.gov", "Tony", "Almeida"));


			// save a Games
			Game game1 = gameRepository.save(new Game(LocalDateTime.now()));
			Game game2 = gameRepository.save(new Game(LocalDateTime.now().plusHours(1)));
			Game game3 = gameRepository.save(new Game(LocalDateTime.now().plusHours(2)));


			// save a GamePlayers
			GamePlayer gp1 = gamePlayerRepository.save(new GamePlayer(game1, jack, LocalDateTime.from(game1.getCreationDate())));
            GamePlayer gp2 = gamePlayerRepository.save(new GamePlayer(game1, chloe, LocalDateTime.from(game1.getCreationDate())));
            GamePlayer gp3 = gamePlayerRepository.save(new GamePlayer(game2, jack, LocalDateTime.from(game2.getCreationDate())));
            GamePlayer gp4 = gamePlayerRepository.save(new GamePlayer(game2, chloe, LocalDateTime.from(game2.getCreationDate())));
            GamePlayer gp5 = gamePlayerRepository.save(new GamePlayer(game3, chloe, LocalDateTime.from(game3.getCreationDate())));
            GamePlayer gp6 = gamePlayerRepository.save(new GamePlayer(game3, tony, LocalDateTime.from(game3.getCreationDate())));


			// save a Ships
			gp1.addShip(new Ship("Destroyer", Arrays.asList("H2", "H3", "H4")));
			gp1.addShip(new Ship("Submarine", Arrays.asList("E1", "F1", "G1")));
			gp1.addShip(new Ship("Patrol Boat", Arrays.asList("B4", "B5")));

			gp2.addShip(new Ship("Destoyer", Arrays.asList("B5", "C5", "D5")));
			gp2.addShip(new Ship("Patrol Boat", Arrays.asList("F1", "F2")));

			gp3.addShip(new Ship("Destroyer", Arrays.asList("B5", "C5", "D5")));
			gp3.addShip(new Ship("Patrol Boat", Arrays.asList("C6", "C7")));

			gp4.addShip(new Ship("Submarine", Arrays.asList("A2", "A3", "A4")));
			gp4.addShip(new Ship("Patrol Boat", Arrays.asList("G6", "H6")));

			gp5.addShip(new Ship("Destroyer", Arrays.asList("B5", "C5", "D5")));
			gp5.addShip(new Ship("Patrol Boat", Arrays.asList("C6", "C7")));

			gp6.addShip(new Ship("Submarine", Arrays.asList("A2", "A3", "A4")));
			gp6.addShip(new Ship("Patrol Boat", Arrays.asList("G6", "H6")));


			// save a salvoes
			gp1.addSalvo(new Salvo(1, Arrays.asList("B5", "C5", "F1")));
			gp1.addSalvo(new Salvo(2, Arrays.asList("F2", "D5")));

			gp2.addSalvo(new Salvo(1, Arrays.asList("B4", "B5", "B6")));
			gp2.addSalvo(new Salvo(2, Arrays.asList("E1", "H3", "A2")));

			gp3.addSalvo(new Salvo(1, Arrays.asList("A2", "A4", "G6")));
			gp3.addSalvo(new Salvo(2, Arrays.asList("A3", "H6")));

			gp4.addSalvo(new Salvo(1, Arrays.asList("B5", "D5", "C7")));
			gp4.addSalvo(new Salvo(2, Arrays.asList("C5", "C6")));

			gp5.addSalvo(new Salvo(1, Arrays.asList("G6", "H6", "A4")));
			gp5.addSalvo(new Salvo(2, Arrays.asList("A2", "A3", "D8")));

			gp6.addSalvo(new Salvo(1, Arrays.asList("H1", "H2", "H3")));
			gp6.addSalvo(new Salvo(2, Arrays.asList("E1", "F2", "G3")));


			// save a gamePlayerRepository
			gamePlayerRepository.save(gp1);
			gamePlayerRepository.save(gp2);
			gamePlayerRepository.save(gp3);
			gamePlayerRepository.save(gp4);
			gamePlayerRepository.save(gp5);
			gamePlayerRepository.save(gp6);


			//save a ScoreRepository
			scoreRepository.save(new Score(3, game1, jack, LocalDateTime.from(game1.getCreationDate().plusMinutes(30))));
			scoreRepository.save(new Score(0, game1, chloe, LocalDateTime.from(game1.getCreationDate().plusMinutes(30))));
			scoreRepository.save(new Score(1, game2, jack, LocalDateTime.from(game2.getCreationDate().plusMinutes(30))));
			scoreRepository.save(new Score(1, game2, chloe, LocalDateTime.from(game2.getCreationDate().plusMinutes(30))));
			scoreRepository.save(new Score(3, game3, chloe, LocalDateTime.from(game3.getCreationDate().plusMinutes(30))));
			scoreRepository.save(new Score(0, game3, tony, LocalDateTime.from(game3.getCreationDate().plusMinutes(30))));


		};
	}
}


