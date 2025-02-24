package ec.edu.uce.pokeweb.api;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Component
public class PokemonApiClient {
    private static final String BASE_URL = "https://pokeapi.co/api/v2/pokemon/";
    private static final String ALL_POKEMON_URL = "https://pokeapi.co/api/v2/pokemon?limit=1025&offset=0";
    private final RestTemplate restTemplate;
    private final long numeroPokemones = 100;
    private final SpringDataWebAutoConfiguration springDataWebAutoConfiguration;

    // Constructor con solo RestTemplate
    public PokemonApiClient(RestTemplate restTemplate, SpringDataWebAutoConfiguration springDataWebAutoConfiguration) {
        this.restTemplate = restTemplate;
        this.springDataWebAutoConfiguration = springDataWebAutoConfiguration;
    }

    /**
     * Obtiene los detalles del Pokémon desde la URL dada.
     */
    public JSONObject getPokemonDetails(String url) {
        String response = restTemplate.getForObject(url, String.class);
        return new JSONObject(response);
    }
    /**
     * Obtiene todas las URLs de los Pokémon disponibles en la API.
     */
    private List<String> getAllPokemonUrls() {
        String response = restTemplate.getForObject(ALL_POKEMON_URL, String.class);
        JSONObject jsonResponse = new JSONObject(response);
        JSONArray resultsArray = jsonResponse.getJSONArray("results");

        return resultsArray.toList().stream()
                .map(obj -> ((HashMap<?, ?>) obj).get("url").toString())  // Convertir HashMap y extraer "url"
                .collect(Collectors.toList());
    }



    /**
     * Obtiene los detalles de los primeros `numberOfPokemons` Pokémon y los pasa a PokemonInfo.
     * El número de Pokémon es una variable interna, no un parámetro.
     */

    public void fetch1asd(PokemonInfo pokemonInfo) {
        long startTime = System.currentTimeMillis(); // Captura el tiempo inicial
        List<String> pokemonUrls = getAllPokemonUrls();
        pokemonUrls
                .parallelStream()
                .forEach(url -> {
                    synchronized (this){
                        JSONObject pokemonData = getPokemonDetails(url);
                        pokemonInfo.fetchAndSavePokemon(pokemonData);

                    }

        });
        long endTime = System.currentTimeMillis(); // Captura el tiempo final
        long duration = endTime - startTime; //

        System.out.println("Se tardo: " + duration/1000.0 + " segundos en guardar "+numeroPokemones+" pokemones");
    }
    public void fetch(PokemonInfo pokemonInfo) {
        long startTime = System.currentTimeMillis(); // Captura el tiempo inicial
        LongStream.rangeClosed(1, numeroPokemones)
                .parallel()
                .forEach(id -> {
                    synchronized (this){
                        String pokemonUrl = BASE_URL + id + "/";
                        JSONObject pokemonData = getPokemonDetails(pokemonUrl);
                        pokemonInfo.fetchAndSavePokemon(pokemonData);

                    }

                });
        long endTime = System.currentTimeMillis(); // Captura el tiempo final
        long duration = endTime - startTime; //

        System.out.println("Se tardo: " + duration/1000.0 + " segundos en guardar "+numeroPokemones+" pokemones");
    }
    public void fetchqw(PokemonInfo pokemonInfo) {
        long startTime = System.currentTimeMillis(); // Captura el tiempo inicial
        LongStream.rangeClosed(1, numeroPokemones)
                .parallel()
                .forEach(id -> {
                    String pokemonUrl = BASE_URL + id + "/";
                    JSONObject pokemonData = getPokemonDetails(pokemonUrl);
                    pokemonInfo.fetchAndSavePokemon(pokemonData);

                });
        long endTime = System.currentTimeMillis(); // Captura el tiempo final
        long duration = endTime - startTime; //

        System.out.println("Se tardo: " + duration/1000.0 + " segundos en guardar "+numeroPokemones+" pokemones");
    }
    public void fetch11(PokemonInfo pokemonInfo) {
        long startTime = System.currentTimeMillis(); // Captura el tiempo inicial
        for (long id = 1; id <= numeroPokemones; id++) {  // Ajustamos el límite del ciclo para que cubra los primeros 100 Pokémon
            // Construir la URL directamente aquí
            String pokemonUrl = BASE_URL + id + "/";
            // Obtener los detalles del Pokémon
            JSONObject pokemonData = getPokemonDetails(pokemonUrl);

            // Llamar a PokemonInfo para guardar los datos
            pokemonInfo.fetchAndSavePokemon(pokemonData);


        }
        long endTime = System.currentTimeMillis(); // Captura el tiempo final
        long duration = endTime - startTime; //

        System.out.println("Se tardo: " + duration/1000.0 + " segundos en guardar "+numeroPokemones+" pokemones");
    }
}
