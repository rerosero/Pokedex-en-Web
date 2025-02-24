package ec.edu.uce.pokeweb.handler;

import ec.edu.uce.pokeweb.api.PokemonApiClient;
import ec.edu.uce.pokeweb.model.entity.Generation;
import ec.edu.uce.pokeweb.repository.GenerationRepository;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class GenerationProcessor {

    private final GenerationRepository generationRepository;
    private final PokemonApiClient apiClient;

    public GenerationProcessor(GenerationRepository generationRepository, PokemonApiClient apiClient) {
        this.generationRepository = generationRepository;
        this.apiClient = apiClient;
    }

    public Generation processGeneration(String generationUrl) {
        // Obtener detalles de la generación desde la API
        JSONObject generationDetails = apiClient.getPokemonDetails(generationUrl);

        // Extraer el nombre de la generación
        String generationName = generationDetails.getJSONObject("generation").getString("name");


        // Buscar si la generación ya está en la base de datos
        Generation generation = generationRepository.findByName(generationName).orElse(new Generation());

        // Establecer el nombre de la generación
        generation.setName(generationName);

        // Guardar la generación en la base de datos si no existe
        generationRepository.save(generation);

        return generation;
    }
}
