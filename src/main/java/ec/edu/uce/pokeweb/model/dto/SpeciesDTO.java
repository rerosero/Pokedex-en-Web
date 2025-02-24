package ec.edu.uce.pokeweb.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
public class SpeciesDTO {
    private long id;
    private String name;
    private Set<String> pokemonNames;

    public SpeciesDTO() {}
    public SpeciesDTO(long id, String name, Set<String> pokemonNames) {
        this.id = id;
        this.name = name;
        this.pokemonNames = pokemonNames;
    }
}
