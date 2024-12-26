package br.com.nazareth.literalura;

import br.com.nazareth.literalura.entity.Autor;
import br.com.nazareth.literalura.entity.Livro;
import br.com.nazareth.literalura.model.DadosGerais;
import br.com.nazareth.literalura.repository.LivroRepositorio;
import br.com.nazareth.literalura.services.ConsumoApi;
import br.com.nazareth.literalura.services.ConverteDados;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Function;

public class Principal {
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books/?search=";
    Scanner scanner = new Scanner(System.in);
    private LivroRepositorio repositorio = new LivroRepositorio() {
        @Override
        public List<Autor> obterInfoAutor() {
            return List.of();
        }

        @Override
        public void flush() {

        }

        @Override
        public <S extends Livro> S saveAndFlush(S entity) {
            return null;
        }

        @Override
        public <S extends Livro> List<S> saveAllAndFlush(Iterable<S> entities) {
            return List.of();
        }

        @Override
        public void deleteAllInBatch(Iterable<Livro> entities) {

        }

        @Override
        public void deleteAllByIdInBatch(Iterable<Long> longs) {

        }

        @Override
        public void deleteAllInBatch() {

        }

        @Override
        public Livro getOne(Long aLong) {
            return null;
        }

        @Override
        public Livro getById(Long aLong) {
            return null;
        }

        @Override
        public Livro getReferenceById(Long aLong) {
            return null;
        }

        @Override
        public <S extends Livro> List<S> findAll(Example<S> example) {
            return List.of();
        }

        @Override
        public <S extends Livro> List<S> findAll(Example<S> example, Sort sort) {
            return List.of();
        }

        @Override
        public <S extends Livro> List<S> saveAll(Iterable<S> entities) {
            return List.of();
        }

        @Override
        public List<Livro> findAll() {
            return List.of();
        }

        @Override
        public List<Livro> findAllById(Iterable<Long> longs) {
            return List.of();
        }

        @Override
        public <S extends Livro> S save(S entity) {
            return null;
        }

        @Override
        public Optional<Livro> findById(Long aLong) {
            return Optional.empty();
        }

        @Override
        public boolean existsById(Long aLong) {
            return false;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(Long aLong) {

        }

        @Override
        public void delete(Livro entity) {

        }

        @Override
        public void deleteAllById(Iterable<? extends Long> longs) {

        }

        @Override
        public void deleteAll(Iterable<? extends Livro> entities) {

        }

        @Override
        public void deleteAll() {

        }

        @Override
        public List<Livro> findAll(Sort sort) {
            return List.of();
        }

        @Override
        public Page<Livro> findAll(Pageable pageable) {
            return null;
        }

        @Override
        public <S extends Livro> Optional<S> findOne(Example<S> example) {
            return Optional.empty();
        }

        @Override
        public <S extends Livro> Page<S> findAll(Example<S> example, Pageable pageable) {
            return null;
        }

        @Override
        public <S extends Livro> long count(Example<S> example) {
            return 0;
        }

        @Override
        public <S extends Livro> boolean exists(Example<S> example) {
            return false;
        }

        @Override
        public <S extends Livro, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
            return null;
        }
    };
    public int selecao;
    private String procurarTitulo = " ";
    private static String opcoesMenu = """
            
            --------- Livros ---------
            
            Como posso te ajudar?
            
            1 - Procurar novo livro pelo nome
            2 - Listar livros registrados
            3 - Listar livros registrados por autor
            4 - Listar livros registrados por gênero
            5 - Listar livros registrados idioma
            6 - Listar livros registrados por ano de lançamento
            
            --------------------------
            """;

    public void selecaoOperacao() {
        System.out.println(opcoesMenu);
        int selecao = scanner.nextInt();
        scanner.nextLine();
        switch (selecao) {
            case 0:
                System.out.println("Saindo do programa. Até mais :)");
                System.exit(0);
            case 1:
                novoLivro();
                break;
            case 2:
                listarLivrosSalvos();
                break;
            case 3:
                listarLivrosSalvosPorAutor();
                break;
            case 4:
                listarLivrosSalvosPorGenero();
                break;
            case 5:
                listarLivrosSalvosPorIdioma();
                break;
            case 6:
                listarAutoresEmUmDeterminadoAno();
                break;
            default:
                System.out.println("Seleção inválida, tente novamente por favor");

        }
    }

    private String entradaUsuario(){
        System.out.println("Qual livro você quer procurar?");
        procurarTitulo = scanner.nextLine();
        return procurarTitulo;
    }

    private DadosGerais obterDadosNaApi(String titulo){
        var json = consumo.obterDados(ENDERECO + procurarTitulo.replace(" ","%20"));
        var dadosGerais = conversor.obterDados(json, DadosGerais.class);
        return dadosGerais;
    }

    public Optional<Livro> buscaDadosLivro(DadosGerais dadosLivro, String titulo) {
        Optional <Livro> livros = dadosLivro.results().stream()
                .filter(l->l.titulo().toLowerCase().contains(titulo.toLowerCase()))
                .map(l-> new Livro(l.titulo(),l.idiomas(),l.downloads(),l.autores()))
                .findFirst();
        return livros;
    }

    private Optional <Livro> novoLivro() {
        String titulo = entradaUsuario();
        DadosGerais dadosGerais = obterDadosNaApi(titulo);
        Optional<Livro> livro = buscaDadosLivro(dadosGerais, titulo);

        if (livro.isPresent()){
            var l = livro.get();
            repositorio.save(l);
            System.out.println(l);
        }else {
            System.out.println("Nenhum livro encontrado :(");
        }
        return livro;
    }

    private void listarLivrosSalvos() {

    }

    private void listarLivrosSalvosPorAutor() {

    }

    private void listarLivrosSalvosPorGenero() {

    }

    private void listarLivrosSalvosPorIdioma() {

    }

    private void listarAutoresEmUmDeterminadoAno() {

    }

}