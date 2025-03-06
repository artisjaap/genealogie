package be.genealogie.repository;

import be.genealogie.GenealogieApplication;
import be.genealogie.TestcontainersConfiguration;
import be.genealogie.domein.entiteit.NatuurlijkPersoon;
import be.genealogie.domein.repository.NatuurlijkPersoonRepository;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = { GenealogieApplication.class, TestcontainersConfiguration.class})
@TestPropertySource(properties = {"spring.config.name=application"})
@ActiveProfiles({"test"})
public class NatuurlijkPersoonRepositoryTest {

    @Resource
    private NatuurlijkPersoonRepository persoonRepository;

    @Test
    public void testSave() {
        NatuurlijkPersoon save = persoonRepository.save(NatuurlijkPersoon.builder().build());

        System.out.println(save);

    }
}
