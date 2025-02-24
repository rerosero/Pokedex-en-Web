package ec.edu.uce.pokeweb.service;

import ec.edu.uce.pokeweb.model.dto.EvolutionDTO;
import ec.edu.uce.pokeweb.model.entity.Evolution;
import ec.edu.uce.pokeweb.repository.EvolutionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvolutionService {
    @Autowired
    private EvolutionRepository evolutionRepository;

    public EvolutionDTO getEvolutionDTO(Long evolutionId) {
        Evolution evolution = evolutionRepository.findById(evolutionId).orElseThrow(() -> new EntityNotFoundException("Evolución no encontrada"));

        EvolutionDTO evolutionDTO = new EvolutionDTO();
        evolutionDTO.setId(evolution.getId());
        evolutionDTO.setBasePokemonId(evolution.getBasePokemon().getId());
        evolutionDTO.setEvolvedPokemonId(evolution.getEvolvedPokemon().getId());

        return evolutionDTO;
    }
}
