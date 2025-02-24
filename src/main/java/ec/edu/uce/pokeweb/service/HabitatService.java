package ec.edu.uce.pokeweb.service;

import ec.edu.uce.pokeweb.model.dto.HabitatDTO;
import ec.edu.uce.pokeweb.model.entity.Habitat;
import ec.edu.uce.pokeweb.model.entity.Pokemon;
import ec.edu.uce.pokeweb.repository.HabitatRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class HabitatService {

    @Autowired
    private HabitatRepository habitatRepository;

    public HabitatDTO getHabitatDTO(Long habitatId) {
        Habitat habitat = habitatRepository.findById(habitatId)
                .orElseThrow(() -> new EntityNotFoundException("Habitat no encontrada"));

        HabitatDTO habitatDTO = new HabitatDTO();
        habitatDTO.setId(habitat.getId());
        habitatDTO.setHabitat(habitat.getName()); // Agregamos el nombre del hábitat

        // Obtenemos los nombres de los Pokémon asociados al hábitat
        Set<String> pokemonNames = habitat.getPokemonList().stream()
                .map(Pokemon::getName)
                .collect(Collectors.toSet());
        habitatDTO.setPokemonNames(pokemonNames);

        return habitatDTO;
    }
}

