package ec.edu.uce.pokeweb.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class PokemonDTO {
    private Long id;
    private String name;
    private Double height;
    private Double weight;
    private String description;
    private String spriteUrl;
    private int hp;
    private int attack;
    private int defense;
    private int specialAttack;
    private int specialDefense;
    private int speed;
    private int total;
    // Relación con Type (puedes incluir solo los nombres de los tipos)
    private Set<String> types;

    // Relación con Ability (solo los nombres de las habilidades)
    private Set<String> abilities;

    // Relación con Evolution (puedes incluir solo los IDs o nombres de los Pokémon evolucionados)
    private Set<Long> evolutionIds;

    // Relación con Generation (solo el nombre de la generación)
    private String generationName;

    // Relación con Habitat (solo el nombre del hábitat)
    private String habitatName;

    // Relación con Species (solo el nombre de la especie)
    private String speciesName;
    public PokemonDTO() {}


    public PokemonDTO(Long id, String name, Double height, Double weight, String description, int hp, int attack, int defense, int speed, int total) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.description = description;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.total = total;
    }

    public PokemonDTO(Long id, String name, Double height, Double weight, String description, String spriteUrl, int hp, int attack, int defense, int speed, int total) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.description = description;
        this.spriteUrl = spriteUrl;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.total = total;
    }
}
