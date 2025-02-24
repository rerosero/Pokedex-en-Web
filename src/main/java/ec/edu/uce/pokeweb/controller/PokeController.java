package ec.edu.uce.pokeweb.controller;

import ec.edu.uce.pokeweb.model.dto.PokemonDTO;
import ec.edu.uce.pokeweb.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//para obtener la informacion y enviarlo a mi fronted
@RestController
@RequestMapping("/api/pokemon")
//http://localhost:8080/api/pokemon
public class PokeController  {
    @Autowired
    private PokemonService pokemonService;

    @GetMapping("/{pokemonId}")
    public ResponseEntity<PokemonDTO> getPokemonDetails(@PathVariable Long pokemonId) {
        PokemonDTO pokemonDTO = pokemonService.getPokemonDTO(pokemonId);
        return ResponseEntity.ok(pokemonDTO);
    }

    // Endpoint para obtener todos los Pokémon
    @GetMapping("/all")
    public ResponseEntity<List<PokemonDTO>> getAllPokemon() {
        List<PokemonDTO> allPokemon = pokemonService.getAllPokemon();
        return ResponseEntity.ok(allPokemon);
    }
}