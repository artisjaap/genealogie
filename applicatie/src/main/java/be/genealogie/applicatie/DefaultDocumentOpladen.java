package be.genealogie.applicatie;

import be.genealogie.applicatie.mapper.NatuurlijkPersoonMapper;
import be.genealogie.domein.dto.DocumentDto;
import be.genealogie.domein.dto.DocumentToegevoegdDto;
import be.genealogie.domein.dto.DocumentTypeDto;
import be.genealogie.domein.dto.RelatieDto;
import be.genealogie.domein.entiteit.Document;
import be.genealogie.domein.entiteit.NatuurlijkPersoon;
import be.genealogie.domein.entiteit.Relatie;
import be.genealogie.domein.repository.DocumentRepository;
import be.genealogie.domein.repository.DocumentTypeRepository;
import be.genealogie.domein.repository.NatuurlijkPersoonRepository;
import be.genealogie.domein.repository.RelatieRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DefaultDocumentOpladen implements DocumentOpladen {

    private final ApplicationProperties applicationProperties;
    private final DocumentRepository documentRepository;
    private final DocumentTypeRepository documentTypeRepository;
    private final NatuurlijkPersoonRepository natuurlijkPersoonRepository;
    private final RelatieRepository relatieRepository;
    private final DocumentTypeZoeken documentTypeZoeken;
    private final ModelMapper modelMapper;

    @Override
    public DocumentToegevoegdDto voegDocumentToe(DocumentDto documentDto) {
        String basePath = applicationProperties.getDocument().getPath();
        DocumentTypeDto documentTypeDto = documentTypeZoeken.getById(documentDto.getDocumentTypeId());

        Relatie relatie = Optional.ofNullable(documentDto.getRelatieId()).map(relatieRepository::getById).orElse(null);
        NatuurlijkPersoon natuurlijkPersoon = Optional.ofNullable(documentDto.getNatuurlijkPersoonId()).map(natuurlijkPersoonRepository::getById).orElse(null);

        String subPath = berekenSubPath(relatie, natuurlijkPersoon, documentTypeDto);
        File file = new File(basePath + subPath, documentDto.getOrigineleFilename());

        DocumentToegevoegdDto.DocumentToegevoegdDtoBuilder builder = DocumentToegevoegdDto.builder()
                .documentType(documentTypeDto)
                .relatieDto(Optional.ofNullable(relatie).map(r -> modelMapper.map(r, RelatieDto.class)).orElse(null))
                .natuurlijkPersoonDTO(Optional.ofNullable(natuurlijkPersoon).map(NatuurlijkPersoonMapper::map).orElse(null))
                .pathNaarDocument(documentDto.getOrigineleFilename());
        try {
            FileUtils.writeByteArrayToFile(file, documentDto.getBytes());

            documentRepository.save(Document.builder()
                    .documentType(documentTypeRepository.getById(documentDto.getDocumentTypeId()))
                    .relatie(relatie)
                    .natuurlijkPersoon(natuurlijkPersoon)
                    .transcript(documentDto.getTranscript())
                    .pathNaarDocument(subPath + documentDto.getOrigineleFilename())

                    .build());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return builder.build();
    }

    private String berekenSubPath(Relatie relatie, NatuurlijkPersoon natuurlijkPersoon, DocumentTypeDto documentTypeDto) {
        if (relatie != null) {
            return documentTypeDto.getCode() + "/R" + relatie.getId() + "/";
        }
        if (natuurlijkPersoon != null) {
            return documentTypeDto.getCode() + "/N" + natuurlijkPersoon.getId() + "/";
        }
        return documentTypeDto.getCode() + "/";
    }
}
