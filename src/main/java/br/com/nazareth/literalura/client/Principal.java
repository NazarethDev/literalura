package br.com.nazareth.literalura.client;

import br.com.nazareth.literalura.entity.Autor;
import br.com.nazareth.literalura.entity.Livro;
import br.com.nazareth.literalura.model.DadosGerais;
import br.com.nazareth.literalura.model.Idiomas;
import br.com.nazareth.literalura.repository.LivroRepositorio;
import br.com.nazareth.literalura.services.ConsumoApi;
import br.com.nazareth.literalura.services.ConverteDados;

import java.util.*;

public class Principal {
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books/?search=";
    Scanner scanner = new Scanner(System.in);
    private LivroRepositorio livroRepositorio;
    private List<Livro> listaLivros;
    private List<Autor> listaAutores;
    private List<Livro> livrosPorIdioma;
    private List<Autor> autoresVivosEmData;
    private List<Livro> quantidadeDeDownloads;

    public Principal(LivroRepositorio livroRepositorio) {
        this.livroRepositorio = livroRepositorio;
    }

    public int selecao;
    private String procurarTitulo = " ";
    private static String opcoesMenu = """
            
            --------- Livros ---------
            
            Como posso te ajudar?
            
            1 - Procurar novo livro pelo título
            2 - Listar livros registrados
            3 - Listar autores registrados
            4 - Apresentar livros com quantidade mínima de downloads
            5 - Livros Salvos por idioma
            6 - Buscar autores em determinado ano
            
            0 - sair
            
            --------------------------
            """;
    private String listaDeIdiomas = """
            pt - português
            en - inglês
            es - espanhol
            fr - francês
            it - italiano
            """;

    public void menuPrincipal() {
        while (selecao != -1) {
            System.out.println(opcoesMenu);
            selecao = scanner.nextInt();
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
                    listarAutores();
                    break;
                case 4:
                    quantidadeDeDownloadsPorNumero();
                    break;
                case 5:
                    listarLivrosSalvosPorIdioma();
                    break;
                case 6:
                    listarAutoresDeUmAno();
                    break;
                default:
                    System.out.println("Seleção inválida, tente novamente por favor");

            }
        }
    }


    private String entradaUsuario() {
        System.out.println("Qual livro você quer procurar?");
        procurarTitulo = scanner.nextLine();
        return procurarTitulo;
    }

    private DadosGerais obterDadosNaApi(String titulo) {
        var json = consumo.obterDados(ENDERECO + procurarTitulo.replace(" ", "%20"));
        var dadosGerais = conversor.obterDados(json, DadosGerais.class);
        return dadosGerais;
    }

    public Optional<Livro> buscaDadosLivro(DadosGerais dadosLivro, String titulo) {
        Optional<Livro> livros = dadosLivro.results().stream()
                .filter(l -> l.titulo().toLowerCase().contains(titulo.toLowerCase()))
                .map(l -> new Livro(l.titulo(), l.idiomas(), l.downloads(), l.autores()))
                .findFirst();
        return livros;
    }

    private Optional<Livro> novoLivro() {
        String titulo = entradaUsuario();
        DadosGerais dadosGerais = obterDadosNaApi(titulo);
        Optional<Livro> livro = buscaDadosLivro(dadosGerais, titulo);

        if (livro.isPresent()) {
            var l = livro.get();
            livroRepositorio.save(l);
            System.out.println(l);
        } else {
            System.out.println("Nenhum livro encontrado :(");
        }
        return livro;
    }

    private void listarLivrosSalvos() {
        listaLivros = livroRepositorio.findAll();
        listaLivros.stream()
                .sorted(Comparator.comparing(Livro::getTitulo))
                .forEach(System.out::println);

    }

    private void listarAutores() {
     listaAutores = livroRepositorio.obterInfoAutor();
     listaAutores.stream()
             .sorted(Comparator.comparing(Autor::getNome))
             .forEach(System.out::println);

    }

    private void quantidadeDeDownloadsPorNumero() {
        System.out.println("Qual a quantidade minima de downloads para um livro que você");
        var downloads = scanner.nextInt();
        quantidadeDeDownloads = livroRepositorio.quantidadeDeDownloads(downloads);
        quantidadeDeDownloads.stream()
                .sorted(Comparator.comparing(Livro::getDownloads))
                .forEach(System.out::println);
    }

    private void listarLivrosSalvosPorIdioma() {
        System.out.println("Selecione o idioma que deseja procurar em seus livros salvos");
        System.out.println(listaDeIdiomas);
        String idiomaSelecionado = scanner.nextLine();
        var idiomaParaBusca = Idiomas.fromString(idiomaSelecionado.toLowerCase().trim());
        livrosPorIdioma = livroRepositorio.findByIdiomas(idiomaParaBusca);
        livrosPorIdioma.stream()
                .forEach(System.out::println);
    }

    private void listarAutoresDeUmAno() {
        System.out.println("Informe um ano em que deseja saber quais autores registrados estavam/estão vivos");
        var date = scanner.nextInt();
        autoresVivosEmData = livroRepositorio.listarAutoresDeUmAno(date);
        autoresVivosEmData.stream()
                .sorted(Comparator.comparing(Autor::getNome))
                .forEach(System.out::println);
    }
}