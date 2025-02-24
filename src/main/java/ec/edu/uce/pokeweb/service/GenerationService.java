package ec.edu.uce.pokeweb.service;

import ec.edu.uce.pokeweb.model.dto.GenerationDTO;
import ec.edu.uce.pokeweb.model.entity.Generation;
import ec.edu.uce.pokeweb.model.entity.Pokemon;
import ec.edu.uce.pokeweb.repository.GenerationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GenerationService {

    @Autowired
    private GenerationRepository generationRepository;

    public GenerationDTO getGenerationDTO(Long generationId) {
        Generation generation = generationRepository.findById(generationId).orElseThrow(() -> new EntityNotFoundException("Generación no encontrada"));

        GenerationDTO generationDTO = new GenerationDTO();
        generationDTO.setId(generation.getId());
        generationDTO.setName(generation.getName());

        // Puedes agregar aquí los nombres de los Pokémon si los necesitas
        Set<String> pokemonNames = generation.getPokemonSet().stream().map(Pokemon::getName).collect(Collectors.toSet());
        generationDTO.setPokemons(pokemonNames);

        return generationDTO;
    }
}
