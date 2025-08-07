package org.example.dao;

import org.example.model.Aluguel;
import org.example.model.AluguelDevolucao;
import org.example.model.Cliente;
import org.example.model.Filme;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

public class AluguelDAO {

    public static void cadastrarAluguel(Aluguel aluguel){
        String sql = "INSERT INTO aluguel (cliente_id, filme_id, dataAluguel) VALUES (?, ?, ?)";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, aluguel.getClienteId());
            stmt.setInt(2, aluguel.getFilmeId());
            stmt.setDate(3, Date.valueOf(aluguel.getDataAluguel()));
            stmt.executeUpdate();

            System.out.println("\nAluguel cadastrado com sucesso!\n");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static ArrayList<AluguelDevolucao> listarAlugueisDevolucoes(){
        ArrayList<AluguelDevolucao> devolucoes = new ArrayList<>();

        String sql = "SELECT a.id, f.titulo, c.nome, a.dataAluguel " +
                "FROM aluguel a " +
                "LEFT JOIN filme f " +
                "ON a.filme_id = f.id " +
                "LEFT JOIN cliente c " +
                "ON a.cliente_id = c.id " +
                "WHERE dataDevolucao IS NULL";

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String tituloFilme = rs.getString("titulo");
                String nomeCliente = rs.getString("nome");
                LocalDate dataAluguel = rs.getDate("dataAluguel").toLocalDate();

                AluguelDevolucao devolucao = new AluguelDevolucao(id, dataAluguel, tituloFilme, nomeCliente);
                devolucoes.add(devolucao);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return devolucoes;
    }

    public static ArrayList<AluguelDevolucao> listarTodosAlugueis(){
        ArrayList<AluguelDevolucao> alugueis = new ArrayList<>();

        String sql = "SELECT a.id, f.titulo, c.nome, a.dataAluguel, a.dataDevolucao " +
                "FROM aluguel a " +
                "LEFT JOIN filme f " +
                "ON a.filme_id = f.id " +
                "LEFT JOIN cliente c " +
                "ON a.cliente_id = c.id";

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String tituloFilme = rs.getString("titulo");
                String clienteNome = rs.getString("nome");
                LocalDate dataAluguel = rs.getDate("dataAluguel").toLocalDate();

                AluguelDevolucao aluguel;

                if(rs.getDate("dataDevolucao") != null){
                    LocalDate dataDevolucao = rs.getDate("dataDevolucao").toLocalDate();
                    aluguel = new AluguelDevolucao(id, dataAluguel, dataDevolucao, tituloFilme, clienteNome);
                }else{
                    aluguel = new AluguelDevolucao(id, dataAluguel, tituloFilme, clienteNome);
                }

                alugueis.add(aluguel);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return alugueis;
    }

    public static void registroDevolucao(int id) {
        String sql = "UPDATE aluguel SET dataDevolucao = ? WHERE id = ?";

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setDate(1, Date.valueOf(LocalDate.now()));
            stmt.setInt(2, id);
            stmt.executeUpdate();

            System.out.println("\nFilme devolvido com sucesso!\n");

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Cliente> listarClientesPorFilme(int id) {
        ArrayList<Cliente> clientes = new ArrayList<>();

        String sql = "SELECT c.id, c.nome, c.email " +
                "from aluguel a " +
                "LEFT JOIN filme f " +
                "ON a.filme_id = f.id " +
                "LEFT JOIN cliente c " +
                "ON a.cliente_id = c.id " +
                "WHERE f.id = ?";

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int clienteId = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");

                Cliente cliente = new Cliente(clienteId, nome, email);
                clientes.add(cliente);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return clientes;
    }

    public static ArrayList<Filme> listarFilmesPorCliente(int id){
        ArrayList<Filme> filmes = new ArrayList<>();

        String sql = "SELECT f.id, f.titulo, f.genero, f.anoLancamento " +
                "from aluguel a " +
                "LEFT JOIN filme f " +
                "ON a.filme_id = f.id " +
                "LEFT JOIN cliente c " +
                "ON a.cliente_id = c.id " +
                "WHERE c.id = ?";

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int filmeId = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String genero = rs.getString("genero");
                int anoLancamento = rs.getInt("anoLancamento");

                Filme filme = new Filme(filmeId, titulo, genero, anoLancamento);
                filmes.add(filme);

            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return filmes;
    }
}
