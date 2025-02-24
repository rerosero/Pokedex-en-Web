package ec.edu.uce.pokeweb.repository;

import ec.edu.uce.pokeweb.model.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypeRepository extends JpaRepository<Type, Long> {
    Optional<Type> findByName(String name);
}
