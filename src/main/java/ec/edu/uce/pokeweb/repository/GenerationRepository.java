package ec.edu.uce.pokeweb.repository;

import ec.edu.uce.pokeweb.model.entity.Generation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenerationRepository extends JpaRepository<Generation, Long> {
    Optional<Generation> findByName(String name);
}
