package org.example.dao;

import org.example.model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAO {

    public static void cadastrarCliente(Cliente cliente){
        String sql = "INSERT INTO cliente (nome, email) VALUES (?, ?)";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.executeUpdate();

            System.out.println("\nCliente cadastrado com sucesso!\n");

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Cliente> listarClientes(){
        ArrayList<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT id, nome, email FROM cliente";

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");

                Cliente cliente = new Cliente(id, nome, email);
                clientes.add(cliente);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return clientes;
    }
}
