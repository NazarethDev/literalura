package br.com.nazareth.literalura.entity;

import br.com.nazareth.literalura.model.DadosAutor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer nascimento;
    private Integer falecimento;
    @ManyToOne
    private Livro livro;

    public Autor () {}

    public Autor(String nome, Integer nascimento, Integer falecimento, Livro livro) {
        this.nome = nome;
        this.nascimento = nascimento;
        this.falecimento = falecimento;
        this.livro = livro;
    }

    public Livro getBook () {
        return livro;
    }

    public void setLivro ( Livro livro ) {
        this.livro = livro;
    }

    public Integer getFalecimento () {
        return falecimento;
    }

    public void setFalecimento ( Integer falecimento ) {
        this.falecimento = falecimento;
    }

    public Integer getNascimento () {
        return nascimento;
    }

    public void setNascimento ( Integer nascimento ) {
        this.nascimento = nascimento;
    }

    public String getNome () {
        return nome;
    }

    public void setNome ( String nome ) {
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
                "nome: '" + nome + '\'' +
                        ", nascimento: " + nascimento +
                        ", falecimento: " + falecimento;
    }
}