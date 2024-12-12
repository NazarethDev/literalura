package br.com.nazareth.literalura;

import java.util.Scanner;

public class Menu {

    Scanner scanner = new Scanner(System.in);
    public int selecao;
    private static String opcoesMenu = """
            
            ------- Livros -------
            
            Como posso te ajudar?
            
            1 - Procurar novo livro pelo nome
            2 - Listar livros registrados
            3 - Listar livros registrados por autor
            4 - Listar livros registrados por gênero
            5 - Listar livros registrados idioma
            6 - Listar livros registrados por ano de lançamento
            """;

    public void selecaoOperacao () {
        System.out.println(opcoesMenu);
        selecao = scanner.nextInt();
        while (selecao != -1) {
            switch (selecao) {
                case 0:
                    System.out.println("Saindo do programa. Até mais:)");
                    System.exit(0);
                case 1:
                     searchNewBookByTitle();
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
                     listarLivrosSalvosPorAnoLancamento();
                     break;
                default:
                    System.out.println("Seleção inválida, tente novamente por favor");
            }
        }

    }

    private static void searchNewBookByTitle() {
        System.out.println("Qual o nome do livro que deseja procurar?");

    }

    private static void listarLivrosSalvos() {

    }

    private static void listarLivrosSalvosPorAutor() {

    }

    private static void listarLivrosSalvosPorGenero() {

    }

    private static void listarLivrosSalvosPorIdioma() {

    }

    private static void listarLivrosSalvosPorAnoLancamento() {

    }


}
