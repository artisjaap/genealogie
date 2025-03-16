package be.genealogie.controller.utils;

import be.genealogie.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DBUtils {

    private final SpringDataJpaDocumentRepository documentRepository;
    private final SpringDataJpaGebruikerRepository gebruikerRepository;
    private final SpringDataJpaGenealogischDriekhoekjeRepository genealogischDriekhoekjeRepository;
    private final SpringDataJpaNatuurlijkPersoonRepository natuurlijkPersoonRepository;
    private final SpringDataJpaRelatieRepository relatieRepository;

    public void cleanDb() {
        documentRepository.deleteAllInBatch();
        gebruikerRepository.deleteAllInBatch();
        genealogischDriekhoekjeRepository.deleteAllInBatch();
        natuurlijkPersoonRepository.deleteAllInBatch();
        relatieRepository.deleteAllInBatch();
    }

}
