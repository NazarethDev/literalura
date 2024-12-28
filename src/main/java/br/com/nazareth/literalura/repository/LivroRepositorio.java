package br.com.nazareth.literalura.repository;

import br.com.nazareth.literalura.entity.Autor;
import br.com.nazareth.literalura.entity.Livro;
import br.com.nazareth.literalura.model.Idiomas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface LivroRepositorio extends JpaRepository<Livro, Long> {
    @Query("SELECT a FROM Livro l JOIN l.autores a")
    List<Autor> obterInfoAutor();

    List<Livro> findByIdiomas(Idiomas idioma);

    @Query("SELECT a FROM Livro l JOIN l.autores a WHERE a.nascimento <= :date AND (a.falecimento IS NULL OR a.falecimento >= :date)")
    List<Autor> listarAutoresDeUmAno(@Param("date") Integer data);

    @Query("SELECT l FROM Livro l WHERE l.downloads >= :downloads")
    List<Livro> quantidadeDeDownloads(@Param("downloads") int downloads);
}