package org.example.dao;

import org.example.model.Filme;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FilmeDAO {

    public static void cadastrarFilme(Filme filme){
        String sql = "INSERT INTO filme (titulo, genero, anoLancamento) VALUES (?, ?, ?)";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, filme.getTitulo());
            stmt.setString(2, filme.getGenero());
            stmt.setInt(3, filme.getAnoLancamento());
            stmt.executeUpdate();

            System.out.println("\nFilme cadastrado com sucesso!\n");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Filme> listarFilmes(){
        ArrayList<Filme> filmes = new ArrayList<>();
        String sql = "SELECT id, titulo, genero, anoLancamento FROM filme";

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String genero = rs.getString("genero");
                int anoLancamento = rs.getInt("anoLancamento");

                Filme filme = new Filme(id, titulo, genero, anoLancamento);
                filmes.add(filme);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return filmes;
    }
}
