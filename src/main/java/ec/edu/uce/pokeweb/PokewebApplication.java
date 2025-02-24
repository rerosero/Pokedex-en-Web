package ec.edu.uce.pokeweb;

import ec.edu.uce.pokeweb.api.PokemonApiClient;
import ec.edu.uce.pokeweb.api.PokemonInfo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PokewebApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokewebApplication.class, args);
	}
	@Bean
	public CommandLineRunner run(PokemonApiClient pokemonApiClient, PokemonInfo pokemonInfo) {
		return args -> {
			pokemonApiClient.fetch(pokemonInfo); // Obtiene y guarda los Pokémon

		};

	}

}
