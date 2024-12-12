package br.com.nazareth.literalura;

import java.util.Scanner;

public class Menu {
    public Scanner scanner = new Scanner(System.in);
    public int selecao = 0;

    public static void selecaoOperacao (int selecao) {
        while (selecao != 0) {
            switch (selecao) {
                case 0:
                    System.out.println("Saindo do programa. Até mais:)");
                    System.exit(0);
                case 1:
                     buscarPorTitulo();
                case 2:
                     buscarLivrosSalvosPorAutor();
                case 3:
                     buscarLivrosSalvosPorGenero();
                case 4:
                     buscarLivrosSalvosPorIdioma();
                case 5:
                     buscarLivrosSalvosPorAno();
                default:
                    System.out.println("Seleção inválida, tente novamente por favor");
            }
        }

    }

    private static void buscarLivrosSalvosPorIdioma() {
    }

    private static void buscarLivrosSalvosPorAno() {
    }

    private static void buscarLivrosSalvosPorGenero() {
    }

    private static void buscarLivrosSalvosPorAutor() {
    }

    private static void buscarPorTitulo() {
    }
}
