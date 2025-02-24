package ec.edu.uce.pokeweb.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
public class Species {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Nombre de la especie (ej. "Pokémon Semilla")

    @OneToMany(mappedBy = "species")
    private Set<Pokemon> pokemon; // Relación con los Pokémon que pertenecen a esta especie

    public Species() {}
}
