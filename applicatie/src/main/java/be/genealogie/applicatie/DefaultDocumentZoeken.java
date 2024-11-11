package be.genealogie.applicatie;

import be.genealogie.domein.dto.DocumentContentDto;
import be.genealogie.domein.dto.DocumentHeaderDto;
import be.genealogie.domein.dto.MediaType;
import be.genealogie.domein.dto.NatuurlijkPersoonDTO;
import be.genealogie.domein.entiteit.Document;
import be.genealogie.domein.entiteit.NatuurlijkPersoon;
import be.genealogie.domein.entiteit.Relatie;
import be.genealogie.domein.repository.DocumentRepository;
import be.genealogie.domein.repository.NatuurlijkPersoonRepository;
import be.genealogie.domein.repository.RelatieRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class DefaultDocumentZoeken implements DocumentZoeken {
    private final ApplicationProperties applicationProperties;
    private final DocumentRepository documentRepository;
    private final NatuurlijkPersoonRepository natuurlijkPersoonRepository;
    private final RelatieRepository relatieRepository;

    @Override
    public List<DocumentHeaderDto> alleDocumentenVan(NatuurlijkPersoonDTO persoonDto) {
        NatuurlijkPersoon persoon = natuurlijkPersoonRepository.getById(persoonDto.getId());
        List<Relatie> relaties = relatieRepository.findByPersoon1OrPersoon2(persoon, persoon);

        List<Document> documenten = documentRepository.findByNatuurlijkPersoon(persoon);
        List<Document> relatieDocumenten = relaties.stream().flatMap(relatie -> documentRepository.findByRelatie(relatie).stream()).toList();

        return Stream.of(documenten, relatieDocumenten)
                .flatMap(List::stream)
                .map(doc -> DocumentHeaderDto.builder().id(doc.getId()).fileName(doc.getPathNaarDocument()).type(doc.getDocumentType().getOmschrijving()).build())
                .toList();
    }

    @Override
    public Optional<DocumentContentDto> documentMetId(Long id) {
        Document document = documentRepository.getById(id);
        File file = new File(applicationProperties.getDocument().getPath(), document.getPathNaarDocument());
        try {
            DocumentContentDto doc = DocumentContentDto.builder()
                    .bytes(FileUtils.readFileToByteArray(file))
                    .mediaType(MediaType.forFilename(file.getName()))
                    .build();
            return Optional.of(doc);
        } catch (IOException e) {
            return Optional.empty();
        }
    }
}
