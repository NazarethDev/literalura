package br.com.nazareth.literalura.model;

import br.com.nazareth.literalura.entity.Livro;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosGerais(
        @JsonAlias("results") List<DadosLivro> results
) {

}
