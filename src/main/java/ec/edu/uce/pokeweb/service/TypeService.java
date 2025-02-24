package ec.edu.uce.pokeweb.service;

import ec.edu.uce.pokeweb.model.dto.TypeDTO;
import ec.edu.uce.pokeweb.model.entity.Type;
import ec.edu.uce.pokeweb.repository.TypeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeService {

    @Autowired
    private TypeRepository typeRepository;

    public TypeDTO getTypeDTO(Long typeId) {
        Type type = typeRepository.findById(typeId).orElseThrow(() -> new EntityNotFoundException("Tipo no encontrado"));

        TypeDTO typeDTO = new TypeDTO();
        typeDTO.setId(type.getId());
        typeDTO.setName(type.getName());
        typeDTO.setSpriteURL(type.getSpriteUrl());

        return typeDTO;
    }
}
