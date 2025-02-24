package ec.edu.uce.pokeweb.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
public class GenerationDTO {
    private long id;
    private String name;
    private Set<String> pokemons;

    public GenerationDTO() {}
    public GenerationDTO(long id, String name, Set<String> pokemons) {
        this.id = id;
        this.name = name;
        this.pokemons = pokemons;
    }
}
