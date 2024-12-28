package br.com.nazareth.literalura.entity;

import br.com.nazareth.literalura.model.DadosAutor;
import br.com.nazareth.literalura.model.DadosLivro;
import br.com.nazareth.literalura.model.Idiomas;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @JsonProperty("title")
    private String titulo;

    @OneToMany(mappedBy = "livro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Autor> autores;
    @Enumerated(EnumType.STRING)
    private Idiomas idiomas;
    private Double downloads;

    public Livro (){}

    public Livro ( List<DadosLivro> results ) {}

    public Livro ( String titulo, List<String> idioma, Double downloads, List<DadosAutor> autores ) {
        this.titulo = titulo;
        this.idiomas = Idiomas.fromString(idioma.get(0));
        this.downloads = downloads;
        this.autores = new ArrayList<>();
        for ( DadosAutor dadosAutor : autores ) {
            Autor autor = new Autor(dadosAutor.nome(), dadosAutor.nascimento(), dadosAutor.falecimento(), this);
            this.autores.add(autor);
        }
    }

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public Double getDownloads () {
        return downloads;
    }

    public void setDownloads ( Double downloads ) {
        this.downloads = downloads;
    }

    public Idiomas getIdiomas () {
        return idiomas;
    }

    public void setIdiomas ( Idiomas idiomas ) {
        this.idiomas = idiomas;
    }

    public String getTitulo () {
        return titulo;
    }

    public void setTitulo ( String titulo ) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores () {
        return autores;
    }

    public void setAutores ( List<Autor> authors ) {

        authors.forEach(e -> e.setLivro(this));
        this.autores = autores;
    }

    @Override
    public String toString () {
        return
                "Title='" + titulo + '\'' + "\n" +
                        "Authors: " + autores + "\n" +
                        "Languages: " + idiomas + "\n" +
                        "Downloads: " + downloads + "\n";
    }
}