package br.com.nazareth.literalura.model;


public enum Idiomas {
    INGLÊS("en"),
    ESPANHOL("es"),
    FRANCÊS("fr"),
    ITALIANO("it"),
    PORTUGUES("pt");

    private String idiomas;

    Idiomas(String idioma) {
        this.idiomas = idioma;
    }

    public static Idiomas fromString(String text) {
        for (Idiomas categoria : Idiomas.values()) {
            if (categoria.idiomas.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhum idioma encontrado: " + text);
    }
}
