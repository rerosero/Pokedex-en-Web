package ec.edu.uce.pokeweb.handler;

import ec.edu.uce.pokeweb.api.PokemonApiClient;
import ec.edu.uce.pokeweb.model.entity.Type;
import ec.edu.uce.pokeweb.repository.TypeRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class TypeProcessor {
    private final TypeRepository typeRepository;
    private final PokemonApiClient apiClient;

    public TypeProcessor(TypeRepository typeRepository, PokemonApiClient apiClient) {
        this.typeRepository = typeRepository;
        this.apiClient = apiClient;
    }

    public Set<Type> processTypes(JSONArray typesArray) {
        Set<Type> types = new HashSet<>();
        for (int i = 0; i < typesArray.length(); i++) {
            String typeName = typesArray.getJSONObject(i).getJSONObject("type").getString("name");
            String typeUrl = typesArray.getJSONObject(i).getJSONObject("type").getString("url");
            JSONObject typeDetails = apiClient.getPokemonDetails(typeUrl);

            String spriteUrl = extractSpriteUrl(typeDetails);
            Type type = typeRepository.findByName(typeName).orElse(new Type());
            type.setName(typeName);
            type.setSpriteUrl(spriteUrl);
            types.add(type);
        }
        return types;
    }

    private String extractSpriteUrl(JSONObject typeDetails) {
        JSONObject sprites = typeDetails.optJSONObject("sprites");
        if (sprites != null) {
            JSONObject firstGeneration = sprites.optJSONObject(sprites.keys().next());
            if (firstGeneration != null) {
                return firstGeneration.optJSONObject(firstGeneration.keys().next()).optString("name_icon");
            }
        }
        return null;
    }
}