package ec.edu.uce.pokeweb.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EvolutionDTO {
    private long id;
    private long basePokemonId ;
    private long evolvedPokemonId ;
    public EvolutionDTO() {}
    public EvolutionDTO(long id, long basePokemonId, long evolvedPokemonId) {
        this.id = id;
        this.basePokemonId = basePokemonId;
        this.evolvedPokemonId = evolvedPokemonId;
    }
}
