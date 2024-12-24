package br.com.nazareth.literalura;

import br.com.nazareth.literalura.entity.Livro;
import br.com.nazareth.literalura.model.DadosGerais;
import br.com.nazareth.literalura.services.ConsumoApi;
import br.com.nazareth.literalura.services.ConverteDados;

import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books/?search=";
    Scanner scanner = new Scanner(System.in);
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
                obterDadosLivro();                break;
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
                listarLivrosSalvosPorAnoLancamento();
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
                .filter(l->l.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                .map(l-> new Livro(l.getTitulo(),l.getIdiomas(),l.getDownloads(),l.getAutores()))
                .findFirst();
        return livros;
    }

//    private void searchNewBookByTitle() {
//        String titulo = procurarTitulo;
//        DadosGerais dados = obterDadosNaApi(titulo);
//        Livro livro = new Livro(dados);
//        System.out.println(livro);
//    }

    private Optional<Livro> obterDadosLivro(){
        String tituloDoLivro = entradaUsuario();
        DadosGerais dadosLivro = obterDadosNaApi(tituloDoLivro);
        Optional<Livro> livro = buscaDadosLivro(dadosLivro,tituloDoLivro);

        if (livro.isPresent()){
            var l = livro.get();
            System.out.println(l);
        }else{
            System.out.println("Não encontrei nenhum livro :(\n");
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

    private void listarLivrosSalvosPorAnoLancamento() {

    }

}