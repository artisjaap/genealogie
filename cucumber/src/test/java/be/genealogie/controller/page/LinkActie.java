package be.genealogie.controller.page;

import java.util.Arrays;

public enum LinkActie {
    NIEUW_PERSOON("nieuw persoon", NavigatieActie.NIEUWE_PERSOON),
    OPSLAAN("opslaan", NavigatieActie.OPSLAAN),
    LOGIN("login", NavigatieActie.LOGIN),
    REGISTREER("registreer", NavigatieActie.REGISTREER),
    WIJZIG_FICHE("wijzig fiche", NavigatieActie.WIJZIG_FICHE),
    RELATIE_TOEVOEGEN("relatie toevoegen", NavigatieActie.RELATIE_TOEVOEGEN),
    OUDERS_TOEVOEGEN("relatie toevoegen", NavigatieActie.OUDERS_TOEVOEGEN),
    KIND_TOEVOEGEN_AAN_RELATIE("relatie toevoegen", NavigatieActie.PERSOON_TOEVOEGEN_AAN_RELATIE),
    ;

    private final String link;
    private final NavigatieActie navigatieActie;

    LinkActie(String link, NavigatieActie navigatieActie) {
        this.link = link;
        this.navigatieActie = navigatieActie;
    }

    public static LinkActie voor(String link){
        return Arrays.stream(LinkActie.values()).filter(actie -> actie.link.equals(link)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Link " + link + " is onbekend"));
    }

    public NavigatieActie navigatieActie(){
        return navigatieActie;
    }
}
