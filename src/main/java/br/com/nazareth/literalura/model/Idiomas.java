package br.com.nazareth.literalura.model;

import java.util.List;

public enum Idiomas {
        ENGLISH("en"),
        SPANISH("es"),
        FRENCH("fr"),
        ITALIAN("it"),
        PORTUGUESE("pt");

        private String languages;

        Idiomas ( String languagesAlura ) {
            this.languages = languagesAlura;
        }

        public static Idiomas fromString(String text) {
            for (Idiomas idioma : Idiomas.values())
                if (idioma.languages.equalsIgnoreCase(text)) {
                    return idioma;
                }
            throw new IllegalArgumentException("Ninguna categoria encontrada: " + text);
        }
}


