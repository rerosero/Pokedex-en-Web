package ec.edu.uce.pokeweb.repository;

import ec.edu.uce.pokeweb.model.entity.Habitat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HabitatRepository extends JpaRepository<Habitat, Long> {
    Optional<Habitat> findByName(String name);
}
