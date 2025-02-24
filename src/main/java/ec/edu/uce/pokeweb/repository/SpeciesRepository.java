package ec.edu.uce.pokeweb.repository;

import ec.edu.uce.pokeweb.model.entity.Species;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpeciesRepository extends JpaRepository<Species, Long> {
    Optional<Species> findByName(String name);
}
