package be.genealogie.controller.steps;

import io.cucumber.java.ParameterType;

public class ParameterTypes {

    @ParameterType(".+")
    public String alfanumeriek(String string){
        return string;
    }

    @ParameterType(".+")
    public String email(String string){
        return string;
    }
}
