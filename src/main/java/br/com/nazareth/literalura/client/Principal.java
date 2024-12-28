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
            \n""";

    public void menuPrincipal() {
        while (selecao != -1) {
            System.out.println(opcoesMenu);
            selecao = scanner.nextInt();
            scanner.nextLine();
            switch (selecao) {
                case 0:
                    System.out.println("\nSaindo do programa. Até mais :)\n");
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
                    System.out.println("\nSeleção inválida, tente novamente por favor\n");
            }
        }
    }


    private String entradaUsuario() {
        System.out.println("\nQual livro você quer procurar?\n");
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
            System.out.println("\nNenhum livro encontrado :(\n");
        }
        return livro;
    }

    private void listarLivrosSalvos() {
        listaLivros = livroRepositorio.findAll();
        if (listaLivros.isEmpty()) {
            System.out.println("\nAinda não há autores registrados! Por favor, tente novamente usar a opção 01 do menu inicial para encontrar um novo livro e autor\n");
        } else {
            System.out.println("\n***** Livros cadastrados no sistema *****\n");
            listaLivros.stream()
                    .sorted(Comparator.comparing(Livro::getTitulo))
                    .forEach(System.out::println);
            System.out.println("\n********************\n");
        }
    }

    private void listarAutores() {
        listaAutores = livroRepositorio.obterInfoAutor();
        if (listaAutores.isEmpty()) {
            System.out.println("\nAinda não há autores registrados! Por favor, tente novamente usar a opção 01 do menu inicial para encontrar um novo livro e autor\n");
        } else {
            System.out.println("\n***** Autores cadastrados no sistema *****\n");
            listaAutores.stream()
                    .sorted(Comparator.comparing(Autor::getNome))
                    .forEach(System.out::println);
            System.out.println("\n********************\n");
        }
    }

    private void quantidadeDeDownloadsPorNumero() {
        System.out.println("\nQual a quantidade minima de downloads para um livro que você\n");
        var downloads = scanner.nextInt();
        quantidadeDeDownloads = livroRepositorio.quantidadeDeDownloads(downloads);
        if (quantidadeDeDownloads.isEmpty()) {
            System.out.println("\nNenhum livro com essa quantidade mínima indicada encontrado\n");
        } else {
            quantidadeDeDownloads.stream()
                    .sorted(Comparator.comparing(Livro::getDownloads))
                    .forEach(System.out::println);
        }
    }

    private void listarLivrosSalvosPorIdioma() {
        System.out.println("\nSelecione o idioma que deseja procurar em seus livros salvos\n");
        System.out.println(listaDeIdiomas);
        String idiomaSelecionado = scanner.nextLine();
        var idiomaParaBusca = Idiomas.fromString(idiomaSelecionado.toLowerCase().trim());
        livrosPorIdioma = livroRepositorio.findByIdiomas(idiomaParaBusca);
        if (livrosPorIdioma.isEmpty()) {
            System.out.println("\nNenhum livro registrado com o idioma informado encontrado\n");
        } else {
            System.out.println("\n***** Livros encontrados *****\n");
            livrosPorIdioma.stream()
                    .forEach(System.out::println);
            System.out.println("\n********************\n");
        }
    }

    private void listarAutoresDeUmAno() {
        System.out.println("\nInforme um ano em que deseja saber quais autores registrados estavam/estão vivos\n");
        var date = scanner.nextInt();
        autoresVivosEmData = livroRepositorio.listarAutoresDeUmAno(date);
        if (autoresVivosEmData.isEmpty()) {
            System.out.println("\nNenhum autor(a) encontrado no ano informado\n");
        } else {
            System.out.println("\n***** Autores encontrado *****\n");
            autoresVivosEmData.stream()
                    .sorted(Comparator.comparing(Autor::getNome))
                    .forEach(System.out::println);
            System.out.println("\n*********************\n");
        }
    }
}