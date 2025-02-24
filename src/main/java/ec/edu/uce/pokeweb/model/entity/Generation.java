package ec.edu.uce.pokeweb.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
public class Generation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;  // Nombre de la generación (Ej. "Generación I", "Generación II", etc.)

    @OneToMany(mappedBy = "generation")
    private Set<Pokemon> pokemonSet;  // Relación con Pokémon

    public Generation() {}

}
