package ec.edu.uce.pokeweb.service;


import ec.edu.uce.pokeweb.model.dto.AbilityDTO;
import ec.edu.uce.pokeweb.model.entity.Ability;
import ec.edu.uce.pokeweb.repository.AbilityRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AbilityService {
    //interaccion con el repositorio de ability
    @Autowired
    private AbilityRepository abilityRepository;

    public AbilityDTO getAbilityByName(String name) {
        //busca la entidad en mi base de datos
        Ability ability= abilityRepository.findByName(name)
                .orElseThrow(()-> new EntityNotFoundException("Habilidad no encontrada"));
        //Convierte la entidad en un DTO
        return new AbilityDTO(
                ability.getId(),
                ability.getName(),
                ability.isHidden(),
                ability.getDescription()
        );
    }
}
