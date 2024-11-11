package be.genealogie.applicatie.validatie;

import be.genealogie.domein.entiteit.NatuurlijkPersoon;
import org.springframework.stereotype.Component;

@Component
public class NatuurlijkPersoonValidator {
    public void valideerVoorInsert(NatuurlijkPersoon natuurlijkPersoon) {
        if(natuurlijkPersoon.getGeslacht() == null){
            throw new IllegalStateException("Gelacht niet gekend");
        }
    }
}
