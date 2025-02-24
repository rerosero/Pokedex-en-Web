package ec.edu.uce.pokeweb.service;

import ec.edu.uce.pokeweb.model.dto.SpeciesDTO;
import ec.edu.uce.pokeweb.model.entity.Pokemon;
import ec.edu.uce.pokeweb.model.entity.Species;
import ec.edu.uce.pokeweb.repository.SpeciesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SpeciesService {

    @Autowired
    private SpeciesRepository speciesRepository;

    public SpeciesDTO getSpeciesDTO(Long speciesId) {
        Species species = speciesRepository.findById(speciesId).orElseThrow(() -> new EntityNotFoundException("Especie no encontrada"));

        SpeciesDTO speciesDTO = new SpeciesDTO();
        speciesDTO.setId(species.getId());
        speciesDTO.setName(species.getName());

        // Puedes agregar aquí los nombres de los Pokémon si los necesitas
        Set<String> pokemonNames = species.getPokemon().stream().map(Pokemon::getName).collect(Collectors.toSet());
        speciesDTO.setPokemonNames(pokemonNames);

        return speciesDTO;
    }
}

