package be.genealogie.applicatie;

import be.genealogie.domein.dto.DocumentTypeDto;
import be.genealogie.domein.repository.DocumentTypeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DefaultDocumentTypeZoeken implements DocumentTypeZoeken {
    private final DocumentTypeRepository documentTypeRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<DocumentTypeDto> alleDocumentTypes() {
        return documentTypeRepository.findAll().stream()
                .map(type -> modelMapper.map(type, DocumentTypeDto.class))
                .toList();
    }
}
