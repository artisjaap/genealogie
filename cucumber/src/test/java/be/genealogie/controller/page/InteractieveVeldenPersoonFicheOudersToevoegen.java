package be.genealogie.controller.page;

import be.genealogie.controller.steps.World;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class InteractieveVeldenPersoonFicheOudersToevoegen extends AbstractInteractiveVelden{

    public InteractieveVeldenPersoonFicheOudersToevoegen(World world) {
        super(world);
    }

    @Override
    public WebPagina huidigePagina() {
        return WebPagina.NATUURLIJK_PERSOON_FICHE_OUDERS_TOEVOEGEN;
    }

    @Override
    public WebPagina navigeerMetActie(NavigatieActie actie) {
        return switch (actie) {
            case OPSLAAN -> WebPagina.NATUURLIJK_PERSOON_FICHE;
            default -> throw new UnsupportedOperationException("Actie " +actie+" bestaat niet op pagina " + huidigePagina());
        };
    }
}
