package org.example.main;

import org.example.dao.ClienteDAO;
import org.example.dao.FilmeDAO;
import org.example.model.Cliente;
import org.example.model.Filme;

import java.util.Date;
import java.util.Scanner;

public class Main {

    private static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        menu();
    }

    public static void menu(){
        boolean sair = false;

        System.out.println("=== LOCADORA DE FILMES ===");
        System.out.println("1. Cadastrar cliente");
        System.out.println("2. Cadastrar filme");
        System.out.println("3. Realizar aluguel");
        System.out.println("4. Devolver filme");
        System.out.println("5. Listar todos os clientes");
        System.out.println("6. Listar todos os filmes");
        System.out.println("7. Listar todos os aluguéis");
        System.out.println("8. Listar aluguéis pendentes");
        System.out.println("9. Listar filmes por cliente");
        System.out.println("10. Listar clientes por filme");
        System.out.println("0. Sair");
        int opcao = SC.nextInt();
        SC.nextLine();

        switch(opcao){
            case 1 -> cadastrarCliente();
            case 2 -> cadastrarFilme();
            case 0 -> {
                sair = true;
                break;
            }
        }

        if(!sair){
            menu();
        }
    }

    public static void cadastrarCliente(){
        System.out.println("\n== Cadastrar um cliente ==");

        System.out.println("Digite o nome do cliente: ");
        String nome = SC.nextLine();

        System.out.println("Digite o email do cliente: ");
        String email = SC.nextLine();

        Cliente cliente = new Cliente(nome, email);
        ClienteDAO.cadastrarCliente(cliente);
    }

    public static void cadastrarFilme(){
        System.out.println("\n== Cadastrar um filme ==");

        System.out.println("Digite o título do filme: ");
        String titulo = SC.nextLine();

        System.out.println("Digite o gênero do filme: ");
        String genero = SC.nextLine();

        System.out.println("Digite o ano de Lançamento do filme: ");
        int anoLancamento = SC.nextInt();

        Filme filme = new Filme(titulo, genero, anoLancamento);
        FilmeDAO.cadastrarFilme(filme);
    }
}