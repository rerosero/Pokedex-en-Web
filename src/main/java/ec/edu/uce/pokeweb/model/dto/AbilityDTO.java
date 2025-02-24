package ec.edu.uce.pokeweb.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AbilityDTO {
    private long id;
    private String name;
    private boolean hidden;
    private String description;
    public AbilityDTO() {}
    public AbilityDTO(long id, String name, boolean hidden, String description) {
        this.id = id;
        this.name = name;
        this.hidden = hidden;
        this.description = description;

    }
}
