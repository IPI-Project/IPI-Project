package com.ipi.games;

import com.ipi.games.domain.Player;
import com.ipi.games.repository.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
//@EnableSwagger2
public class GameApplication {


	public static void main(String[] args) {

		SpringApplication.run(GameApplication.class, args);
	}

	/*@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)//
				.select()//
				.apis(RequestHandlerSelectors.any())//
				.paths(PathSelectors.any()).build();
	}*/


    /*@Bean
    public CommandLineRunner demo(PlayerRepository playerRepository) {
        return (args) -> {

            //save a couple of players
            playerRepository.save(new Player("paul", "paul@mail.com", new BCryptPasswordEncoder().encode("paul")));
            playerRepository.save(new Player("mary", "mary@mary.com",  new BCryptPasswordEncoder().encode("mary")));

        };
    }*/

}
