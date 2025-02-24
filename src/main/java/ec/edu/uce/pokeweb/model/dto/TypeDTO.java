package ec.edu.uce.pokeweb.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypeDTO {
    private long id;
    private String name;
    private String spriteURL;

    public TypeDTO() {}
    public TypeDTO(long id, String name, String spriteURL) {
        this.id = id;
        this.name = name;
        this.spriteURL = spriteURL;
    }

}
