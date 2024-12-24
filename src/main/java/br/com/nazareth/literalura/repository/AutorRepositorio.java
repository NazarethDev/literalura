//package br.com.nazareth.literalura.repository;
//
//import br.com.nazareth.literalura.entity.Autor;
//import br.com.nazareth.literalura.entity.Livro;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import java.util.List;
//import java.util.Optional;
//
//public interface AutorRepositorio extends JpaRepository<Autor, Long> {
//    Optional<Autor> findByNomeContainingIgnoreCase(String nome);
//
//    @Query("SELECT l FROM Autor a JOIN a.livros l WHERE a.nome ILIKE %:nome%")
//    List<Livro> listarLivrosSalvosPorAutor(String nome);
//}