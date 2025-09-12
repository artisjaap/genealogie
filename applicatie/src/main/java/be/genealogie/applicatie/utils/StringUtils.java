package be.genealogie.applicatie.utils;

import java.text.Normalizer;
import java.util.Optional;

public class StringUtils {

    public static String geldigeBestandsnaam(String string) {
        return normaliseerString(string).replaceAll("[/:\\\\*\"<>]", "");

    }

    public static String normaliseerString(String string){
        return Optional.ofNullable(string)
                .map(String::trim)
                .map(StringUtils::asciiTekensEnDiakritischeTekensOpsplitsen)
                .map(StringUtils::verwijderAccenten)
                .orElse("");

    }


    private static String asciiTekensEnDiakritischeTekensOpsplitsen(String s){
        return Normalizer.normalize(s, Normalizer.Form.NFKD);
    }

    private static String verwijderAccenten(String s) {
        return s.replaceAll("\\p{M}", "");
    }
}
