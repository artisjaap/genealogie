package be.genealogie.applicatie;

import be.genealogie.domein.dto.DocumentDto;
import be.genealogie.domein.dto.DocumentToegevoegdDto;
import be.genealogie.domein.dto.NatuurlijkPersoonDTO;
import be.genealogie.domein.dto.RelatieDto;
import be.genealogie.domein.entiteit.Document;
import be.genealogie.domein.repository.DocumentRepository;
import be.genealogie.domein.repository.DocumentTypeRepository;
import be.genealogie.domein.repository.NatuurlijkPersoonRepository;
import be.genealogie.domein.repository.RelatieRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DefaultDocumentOpladen implements DocumentOpladen{
    
    private final ApplicationProperties applicationProperties;
    private final DocumentRepository documentRepository;
    private final DocumentTypeRepository documentTypeRepository;
    private final NatuurlijkPersoonRepository natuurlijkPersoonRepository;
    private final RelatieRepository relatieRepository;

    @Override
    public DocumentToegevoegdDto voegDocumentToe(DocumentDto documentDto) {
        String path = applicationProperties.getDocument().getPath();
        File file = new File(path, documentDto.getOrigineleFilename());
        DocumentToegevoegdDto.DocumentToegevoegdDtoBuilder builder = DocumentToegevoegdDto.builder()
            .documentType(documentDto.getDocumentType())
                .relatieDto(documentDto.getRelatieDto())
                .natuurlijkPersoonDTO(documentDto.getNatuurlijkPersoonDTO())
                .pathNaarDocument(documentDto.getOrigineleFilename());
        try {
            FileUtils.writeByteArrayToFile(file, documentDto.getBytes());

            documentRepository.save(Document.builder()
                            .documentType(documentTypeRepository.getById(documentDto.getDocumentType().getId()))
                            .relatie(Optional.ofNullable(documentDto.getRelatieDto()).map(RelatieDto::getId).map(relatieRepository::getById).orElse(null))
                            .natuurlijkPersoon(Optional.ofNullable(documentDto.getNatuurlijkPersoonDTO()).map(NatuurlijkPersoonDTO::getId).map(natuurlijkPersoonRepository::getById).orElse(null))
                            .pathNaarDocument(file.getAbsolutePath())

                    .build());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return builder.build();
    }
}
