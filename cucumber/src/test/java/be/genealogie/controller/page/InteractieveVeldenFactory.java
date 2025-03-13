package be.genealogie.controller.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class InteractieveVeldenFactory {
    @Autowired
    private List<InteractieveVelden> interactieveVelden;

    public InteractieveVelden voorPagina(WebPagina webPagina) {
        return interactieveVelden.stream()
                .filter(pagina -> pagina.huidigePagina() == webPagina)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Geen pagina implementatie voor " + webPagina));
    }
}
