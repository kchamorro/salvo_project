package com.webcode.kc.salvo;

//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;

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


	//@Bean
	/*
	public CommandLineRunner initData(PlayerRepository repository) {
		return (args) -> {
			// save a Players
			repository.save(new Player(1, "Jack", "Bauer", "j.bauer@ctu.gov"));
			repository.save(new Player(2, "Chloe", "O'Brian", "c.obrian@ctu.gov"));
			repository.save(new Player(3, "Kim", "Bauer","kim_bauer@gmail.com"));
			repository.save(new Player(4, "Tony", "Almeida", "t.almeida@ctu.gov"));
		};
	}
	*/

	/*
	public CommandLineRunner initData(GameRepository repository) {
		return (args) -> {
			// save a Games
			Date date = new Date();
			repository.save(new Game(1, date));
			repository.save(new Game(2, sumarHora(date,1)));
			repository.save(new Game(3, sumarHora(date,2) ));
		};
	}
	*/

	/*
	public Date sumarHora (Date fecha, int horas){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha); // Configuramos la fecha que se recibe
		calendar.add(Calendar.HOUR, horas);  // horas a añadir
		return calendar.getTime(); // Devuelve el objeto Date con las nuevas horas añadidas
	}
	*/

	/*
	public CommandLineRunner initData(GamePlayerRepository repository) {
		return (args) -> {
			// save a GamePlayer
			Date date = new Date();
			repository.save(new GamePlayer(1, date, 1, 1));
			repository.save(new GamePlayer(2, sumarHora(date,1), 2, 1));
			repository.save(new GamePlayer(3, sumarHora(date,2), 4, 2));
			repository.save(new GamePlayer(4, sumarHora(date,4), 2, 3));
			repository.save(new GamePlayer(5, sumarHora(date,4),4,4));
			repository.save(new GamePlayer(6, sumarHora(date,5), 1,5));
		};
	}
	 */
}
