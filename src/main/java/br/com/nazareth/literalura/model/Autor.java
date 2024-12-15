package br.com.nazareth.literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="Autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "Id autor")
    private Long id;
    @Column(name="Nome")
    private String nome;
    @Column(name="Idioma")
    private String idioma;
    @Column(name="nascimento")
    private int nascimento;
    @Column(name="falecimento")
    private int falecimento;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> livros;

    public Autor(DadosAutor dadosAutor){
        this.nome = dadosAutor.nome();
        this.nascimento = dadosAutor.nascimento();
        this.falecimento = dadosAutor.falecimento();
        this.idioma = dadosAutor.idioma();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    public int getNascimento() {
        return nascimento;
    }

    public void setNascimento(int nascimento) {
        this.nascimento = nascimento;
    }

    public int getFalecimento() {
        return falecimento;
    }

    public void setFalecimento(int falecimento) {
        this.falecimento = falecimento;
    }

    @Override
    public String toString() {
        return
                "  nome: " + nome + '\'' +
                ", idioma: " + idioma + '\'' +
                ", nascimento: " + nascimento +
                ", falecimento: " + falecimento +
                ", livros=" + livros;
    }
}