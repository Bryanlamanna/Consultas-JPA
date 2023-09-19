/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.consultasjpa.consultasjpa.persistencia;

import static com.consultasjpa.consultasjpa.persistencia.ConectDB.conn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author EvaGL
 */
                      
public class UsuarioDAO {
    

    public static Usuario validarUsuario(Usuario usuario) {
        // Criando consulta parametrizada
        String sql = "SELECT * FROM usuario WHERE login = ? AND senha = ?;";
        Usuario usuarioEncontrado = null;
        String sql2 = null;

        try {
            ConectDB.conectar();
            PreparedStatement statement = conn.prepareStatement(sql);

            // Atribuindo os valores na consulta
            statement.setString(1, usuario.getLogin());
            statement.setString(2, usuario.getSenha());
            ResultSet rs = statement.executeQuery();
            sql2 = statement.toString();
            while (rs.next()) {
                usuarioEncontrado = new Usuario();
                usuarioEncontrado.setId(rs.getInt("idUsuario"));
                usuarioEncontrado.setNome(rs.getString("nome"));
                usuarioEncontrado.setLogin(rs.getString("login"));
                usuarioEncontrado.setSenha(rs.getString("senha"));
                usuarioEncontrado.setTipo(rs.getString("tipo"));
            }
            
        } catch (SQLException ex) {
            System.out.println("Sintaxe de comando invalida\n"+sql2+"\n"+ex.getMessage());
        }

        return usuarioEncontrado;
    }
                          
}
