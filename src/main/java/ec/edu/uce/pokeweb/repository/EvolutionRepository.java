package ec.edu.uce.pokeweb.repository;

import ec.edu.uce.pokeweb.model.entity.Evolution;
import ec.edu.uce.pokeweb.model.entity.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EvolutionRepository extends JpaRepository<Evolution, Long> {
    Optional<Evolution> findByBasePokemonAndEvolvedPokemon(Pokemon basePokemon, Pokemon evolvedPokemon);
}
