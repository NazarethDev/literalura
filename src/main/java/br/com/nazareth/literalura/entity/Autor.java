package br.com.nazareth.literalura.entity;

import br.com.nazareth.literalura.model.DadosAutor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "authors")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty("name")
    private String nome;
    @JsonProperty("birth_year")
    private Integer nascimento;
    @JsonProperty("death_year")
    private Integer morte;
    @ManyToOne
    private Livro livro;

    public Autor () {}

    public Autor( Autor autor ){}

    public Autor(String nome, Integer nascimento, Integer morte, Livro livro) {
        this.nome = nome;
        this.nascimento = nascimento;
        this.morte = morte;
        this.livro = livro;
    }


    public Livro getLivro () {
        return livro;
    }

    public void setLivro ( Livro book ) {
        this.livro = book;
    }

    public Integer getMorte () {
        return morte;
    }

    public void setMorte ( Integer deathYear ) {
        this.morte = morte;
    }

    public Integer getNascimento () {
        return nascimento;
    }

    public void setNascimento ( Integer birthYear ) {
        this.nascimento = nascimento;
    }

    public String getNome () {
        return nome;
    }

    public void setNome ( String name ) {
        this.nome = nome;
    }

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    @Override
    public String toString () {
        return
                "nome='" + nome + '\'' +
                        ", nascimento: " + nascimento +
                        ", falescimento: " + morte;
    }
}