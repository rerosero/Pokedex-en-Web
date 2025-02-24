package ec.edu.uce.pokeweb.handler;

import ec.edu.uce.pokeweb.api.PokemonApiClient;
import ec.edu.uce.pokeweb.model.entity.Ability;
import ec.edu.uce.pokeweb.repository.AbilityRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class AbilityProcessor {
    private final AbilityRepository abilityRepository;
    private final PokemonApiClient apiClient;
    private static final Logger logger = LoggerFactory.getLogger(AbilityProcessor.class);

    public AbilityProcessor(AbilityRepository abilityRepository, PokemonApiClient apiClient) {
        this.abilityRepository = abilityRepository;
        this.apiClient = apiClient;
    }

    public Set<Ability> processAbilities(JSONArray abilitiesArray) {
        Set<Ability> abilities = new HashSet<>();
        for (int i = 0; i < abilitiesArray.length(); i++) {
            String abilityName = abilitiesArray.getJSONObject(i).getJSONObject("ability").getString("name");
            String abilityUrl = abilitiesArray.getJSONObject(i).getJSONObject("ability").getString("url");
            JSONObject abilityDetails = apiClient.getPokemonDetails(abilityUrl);

            String description = extractDescription(abilityDetails);
            boolean hidden = abilitiesArray.getJSONObject(i).getBoolean("is_hidden");

            //Ability ability = abilityRepository.findByName(abilityName).orElse(new Ability());
            //ability.setName(abilityName);
            //ability.setDescription(description);
            //ability.setHidden(hidden);
            //abilities.add(ability);

            try {
                Ability ability = abilityRepository.findByName(abilityName).orElse(new Ability());
                ability.setName(abilityName);
                ability.setDescription(description);
                ability.setHidden(hidden);
                abilities.add(ability);
            } catch (Exception e) {
                System.out.println("----------------------------l-----------");
                logger.error("Error procesando la habilidad '{}': {}", abilityName, e.getMessage(), e);
            }
        }
        return abilities;
    }

    private String extractDescription(JSONObject abilityDetails) {
        // Aquí asumimos que la descripción de la habilidad está en el campo "flavor_text_entries" (si está disponible)
        JSONArray flavorTextEntries = abilityDetails.optJSONArray("flavor_text_entries");
        if (flavorTextEntries != null) {
            for (int i = 0; i < flavorTextEntries.length(); i++) {
                JSONObject entry = flavorTextEntries.getJSONObject(i);
                if (entry.getJSONObject("language").getString("name").equals("es")) {
                    return entry.getString("flavor_text");
                }
            }
        }
        return "No description available.";
    }
}
