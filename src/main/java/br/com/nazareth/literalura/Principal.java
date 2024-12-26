package br.com.nazareth.literalura;

import br.com.nazareth.literalura.entity.Livro;
import br.com.nazareth.literalura.model.DadosGerais;
import br.com.nazareth.literalura.repository.LivroRepositorio;
import br.com.nazareth.literalura.services.ConsumoApi;
import br.com.nazareth.literalura.services.ConverteDados;

import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books/?search=";
    Scanner scanner = new Scanner(System.in);
    private LivroRepositorio repositorio;
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