package ec.edu.uce.pokeweb.model.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Set;
@Getter
@Setter
public class HabitatDTO {
    private long id;
    private String habitat;
    private Set<String> pokemonNames;
     public HabitatDTO() {}
    public HabitatDTO(long id, String habitat, Set<String> pokemonNames) {
         this.id = id;
         this.habitat = habitat;
         this.pokemonNames = pokemonNames;
    }
}
