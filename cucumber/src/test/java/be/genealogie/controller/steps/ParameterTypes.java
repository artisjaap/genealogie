package be.genealogie.controller.steps;

import be.genealogie.controller.page.LinkActie;
import be.genealogie.domein.Geslacht;
import io.cucumber.java.ParameterType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ParameterTypes {

    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @ParameterType(".+")
    public String alfanumeriek(String string){
        return string;
    }

    @ParameterType(".+")
    public String email(String string){
        return string;
    }

    @ParameterType("man|vrouw")
    public Geslacht geslacht(String string){
        return "man".equals(string) ? Geslacht.MAN : Geslacht.VROUW;
    }

    @ParameterType("\\[(.*)\\]")
    public String referentie(String string){
        return string;
    }

    @ParameterType("[0-9]{2}/[0-9]{2}/[0-9]{4}")
    public LocalDate datum(String datum){
        return LocalDate.parse(datum, dateFormat);
    }

    @ParameterType(".*")
    public LinkActie linkActie(String link){
        return LinkActie.voor(link);
    }
}
