package ec.edu.uce.pokeweb.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
public class Habitat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "habitat")
    private Set<Pokemon> pokemonList;  // Relación inversa con Pokémon

    public Habitat() {}
}
