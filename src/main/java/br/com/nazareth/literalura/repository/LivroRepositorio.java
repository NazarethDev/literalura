package br.com.nazareth.literalura.repository;

import br.com.nazareth.literalura.entity.Autor;
import br.com.nazareth.literalura.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LivroRepositorio extends JpaRepository<Livro, Long> {
    Optional<Autor> findByNomeContainingIgnoreCase(String nome);

    @Query("Select a from Livro l join l.dadosAutor a")
    List<Autor> listarLivrosSalvosPorAutor();

    @Query("SELECT a FROM Livro JOIN l.autores a")
    List<Autor>listarLivrosSalvosPorAutor(Integer data);

    List<Livro> listarLivrosSalvosPorIdioma();
}