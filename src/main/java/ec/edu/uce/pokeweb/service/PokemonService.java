package ec.edu.uce.pokeweb.service;

import ec.edu.uce.pokeweb.model.dto.AbilityDTO;
import ec.edu.uce.pokeweb.model.dto.PokemonDTO;
import ec.edu.uce.pokeweb.model.entity.Ability;
import ec.edu.uce.pokeweb.model.entity.Pokemon;
import ec.edu.uce.pokeweb.model.entity.Type;
import ec.edu.uce.pokeweb.repository.PokemonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class PokemonService {
    @Autowired
    private PokemonRepository pokemonRepository;

    public PokemonDTO getPokemonName(String name) {
        Pokemon pokemon = pokemonRepository.findByName(name).
                orElseThrow(()->new EntityNotFoundException("Pokemon no encontrado"));
        return new PokemonDTO(
                pokemon.getId(),
                pokemon.getName(),
                pokemon.getHeight(),
                pokemon.getWeight(),
                pokemon.getDescription(),
                pokemon.getHp(),
                pokemon.getAttack(),
                pokemon.getDefense(),
                pokemon.getSpeed(),
                pokemon.getTotal());
    }

     public PokemonDTO getPokemonDTO(Long pokemonId) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new EntityNotFoundException("Pokemon no encontradp"));

        PokemonDTO pokemonDTO = new PokemonDTO();
        pokemonDTO.setId(pokemon.getId());
        pokemonDTO.setName(pokemon.getName());
        pokemonDTO.setHeight(pokemon.getHeight());
        pokemonDTO.setWeight(pokemon.getWeight());
        pokemonDTO.setDescription(pokemon.getDescription());
        pokemonDTO.setSpriteUrl(pokemon.getSpriteUrl());
        pokemonDTO.setHp(pokemon.getHp());
        pokemonDTO.setAttack(pokemon.getAttack());
        pokemonDTO.setDefense(pokemon.getDefense());
        pokemonDTO.setSpecialAttack(pokemon.getSpecialAttack());
        pokemonDTO.setSpecialDefense(pokemon.getSpecialDefense());
        pokemonDTO.setSpeed(pokemon.getSpeed());
        pokemonDTO.setTotal(pokemon.getTotal());

        // Relación con tipos (solo los nombres de tipos)
        Set<String> types = pokemon.getTypes().stream().map(Type::getName).collect(Collectors.toSet());
        pokemonDTO.setTypes(types);

        // Relación con habilidades (solo los nombres de habilidades)
        Set<String> abilities = pokemon.getAbilities().stream().map(Ability::getName).collect(Collectors.toSet());
        pokemonDTO.setAbilities(abilities);

        // Relación con evoluciones (solo los IDs de los Pokémon evolucionados)
        Set<Long> evolutionIds = pokemon.getEvolutions().stream().map(e -> e.getEvolvedPokemon().getId()).collect(Collectors.toSet());
        pokemonDTO.setEvolutionIds(evolutionIds);

        // Relación con generación (solo el nombre de la generación)
        pokemonDTO.setGenerationName(pokemon.getGeneration().getName());

        // Relación con hábitat (solo el nombre del hábitat)
        pokemonDTO.setHabitatName(pokemon.getHabitat().getName());

        // Relación con especie (solo el nombre de la especie)
        pokemonDTO.setSpeciesName(pokemon.getSpecies().getName());

        return pokemonDTO;
    }

    public List<PokemonDTO> getAllPokemon() {
        // Obtener todos los Pokémon desde la base de datos
        List<Pokemon> pokemons = pokemonRepository.findAll();

        // Convertir cada Pokémon en un DTO y almacenarlos en una lista
        List<PokemonDTO> pokemonDTOs = new ArrayList<>();
        for (Pokemon pokemon : pokemons) {
            PokemonDTO pokemonDTO = new PokemonDTO(
                    pokemon.getId(),
                    pokemon.getName(),
                    pokemon.getHeight(),
                    pokemon.getWeight(),
                    pokemon.getDescription(),
                    pokemon.getSpriteUrl(),
                    pokemon.getHp(),
                    pokemon.getAttack(),
                    pokemon.getDefense(),
                    pokemon.getSpeed(),
                    pokemon.getTotal()
            );
            pokemonDTOs.add(pokemonDTO); // Agregar el DTO a la lista
        }

        // Ordenar por name (alfabéticamente)
        pokemonDTOs.sort(Comparator.comparing(PokemonDTO::getId)); // Orden numerico

        return pokemonDTOs; // Retornar la lista de Pokémon DTOs ordenada
    }


}
