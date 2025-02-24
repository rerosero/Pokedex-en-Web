package ec.edu.uce.pokeweb.api;

import ec.edu.uce.pokeweb.handler.*;
import ec.edu.uce.pokeweb.model.entity.*;
import ec.edu.uce.pokeweb.repository.PokemonRepository;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class PokemonInfo {

    private final PokemonApiClient pokemonApiClient;
    private final PokemonRepository pokemonRepository;

    private final TypeProcessor typeProcessor;
    private final AbilityProcessor abilityProcessor;
    private final EvolutionProcessor evolutionProcessor;
    private final GenerationProcessor generationProcessor;
    private final HabitatProcessor habitatProcessor;
    private final PokemonProcessor pokemonProcessor;
    private final SpeciesProcessor speciesProcessor;

    public PokemonInfo(PokemonApiClient pokemonApiClient,
                       PokemonRepository pokemonRepository,
                       TypeProcessor typeProcessor,
                       AbilityProcessor abilityProcessor,
                       EvolutionProcessor evolutionProcessor,
                       GenerationProcessor generationProcessor,
                       HabitatProcessor habitatProcessor,
                       PokemonProcessor pokemonProcessor,
                       SpeciesProcessor speciesProcessor) {
        this.pokemonApiClient = pokemonApiClient;
        this.pokemonRepository = pokemonRepository;

        this.typeProcessor = typeProcessor;
        this.abilityProcessor = abilityProcessor;
        this.evolutionProcessor = evolutionProcessor;
        this.generationProcessor = generationProcessor;
        this.habitatProcessor = habitatProcessor;
        this.pokemonProcessor = pokemonProcessor;
        this.speciesProcessor = speciesProcessor;
    }

    @Transactional
    public void fetchAndSavePokemon(JSONObject pokemonData) {


        Pokemon pokemon = pokemonProcessor.processPokemon(pokemonData);

        // Procesar tipos
        Set<Type> types = typeProcessor.processTypes(pokemonData.getJSONArray("types"));
        //procesar ability
        Set<Ability> abilities = abilityProcessor.processAbilities(pokemonData.getJSONArray("abilities"));  // Procesar habilidades
        // Procesar generación
        Generation generation = generationProcessor.processGeneration(pokemonData.getJSONObject("species").getString("url"));
        // Procesar hábitat
        Habitat habitat = habitatProcessor.processHabitat(pokemonData.getJSONObject("species").getString("url"));
        // Procesar especies
        Species species = speciesProcessor.processSpecies(pokemonData.getJSONObject("species").getString("url"));








        pokemon.setTypes(types);
        pokemon.setAbilities(abilities);
        pokemon.setGeneration(generation);
        pokemon.setHabitat(habitat);
        pokemon.setSpecies(species);


        pokemonRepository.save(pokemon);

        // Procesar y guardar las evoluciones
        evolutionProcessor.processEvolutions(pokemonData.getJSONObject("species").getString("url"), pokemon.getName());
    }


}


