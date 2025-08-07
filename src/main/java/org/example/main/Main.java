package org.example.main;

import org.example.dao.AluguelDAO;
import org.example.dao.ClienteDAO;
import org.example.dao.FilmeDAO;
import org.example.model.Aluguel;
import org.example.model.AluguelDevolucao;
import org.example.model.Cliente;
import org.example.model.Filme;

import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class Main {

    private static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        menu();
    }

    public static void menu(){
        boolean sair = false;

        System.out.println("\n=== LOCADORA DE FILMES ===");
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
        System.out.println("Escolha uma opção_ ");
        int opcao = SC.nextInt();
        SC.nextLine();

        switch(opcao){
            case 1 -> cadastrarCliente();
            case 2 -> cadastrarFilme();
            case 3 -> realizarAluguel();
            case 4 -> devolverFilme();
            case 5 -> listarClientes();
            case 6 -> listarFilmes();
            case 7 -> listarAlugueis();
            case 8 -> listarAlugueisPendentes();
            case 9 -> listarFilmesPorCliente();
            case 10 -> listarClientesPorFilme();
            case 0 -> {
                sair = true;
                break;
            }
            default -> System.out.println("Opção Inválida.");
        }

        if(!sair){
            menu();
        }
    }

    private static void listarClientesPorFilme() {
        listarFilmes();

        System.out.println("\nDigite o id do filme para ver quais clientes alugaram: ");
        int id = SC.nextInt();

        for(Cliente c : AluguelDAO.listarClientesPorFilme(id)){
            System.out.println(c);
        }
    }

    private static void listarFilmesPorCliente() {
        listarClientes();

        System.out.println("\nDigite o id do cliente para consultar os aluguéis: ");
        int id = SC.nextInt();

        for(Filme f : AluguelDAO.listarFilmesPorCliente(id)){
            System.out.println(f);
        }
    }

    private static void devolverFilme() {
        System.out.println("\n== Devolver Filme ==");
        listarAlugueisPendentes();

        System.out.println("Digite o id do aluguel que deseja delvover: ");
        int id = SC.nextInt();
        SC.nextLine();

        AluguelDAO.registroDevolucao(id);
    }

    public static void realizarAluguel(){
        System.out.println("\n== Realizar Aluguel ==");

        listarClientes();

        System.out.println("Digite o id do cliente que ira alocar o filme: ");
        int clienteId = SC.nextInt();
        SC.nextLine();

        listarFilmes();

        System.out.println("Digite o id do filme que ira ser alocado: ");
        int filmeId = SC.nextInt();
        SC.nextLine();

        LocalDate dataAluguel = LocalDate.now();

        Aluguel aluguel = new Aluguel(clienteId, filmeId, dataAluguel, null);
        AluguelDAO.cadastrarAluguel(aluguel);
    }

    private static void listarAlugueis() {
        System.out.println("\n=== Histórico de Aluguéis ===");
        for(AluguelDevolucao a : AluguelDAO.listarTodosAlugueis()){
            System.out.println(a);
            if(a.getDataDevolucao() != null){
                System.out.println("Data Devolução - " + a.getDataDevolucao());
            }
        }
    }

    public static void listarAlugueisPendentes(){
        System.out.println("\nFilmes Alugados: ");
        for(AluguelDevolucao a : AluguelDAO.listarAlugueisDevolucoes()){
            System.out.println(a);
        }
    }

    public static void listarFilmes(){
        System.out.println("\nFilmes: ");
        for(Filme f : FilmeDAO.listarFilmes()){
            System.out.println(f);
        }
    }

    public static void listarClientes(){
        System.out.println("\nClientes: ");
        for(Cliente c : ClienteDAO.listarClientes()){
            System.out.println(c);
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
        SC.nextLine();

        Filme filme = new Filme(titulo, genero, anoLancamento);
        FilmeDAO.cadastrarFilme(filme);
    }
}