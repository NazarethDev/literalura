package br.com.nazareth.literalura.model;


public enum Idiomas {
    INGLÊS("en"),
    ESPANHOL("es"),
    FRANCÊS("fr"),
    ITALIANO("it"),
    PORTUGUES("pt");

    private String idiomas;

    Idiomas(String idioma) { // Nome do parâmetro alterado para "idioma"
        this.idiomas = idioma; // Agora, o valor correto é atribuído ao atributo "idiomas"
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
