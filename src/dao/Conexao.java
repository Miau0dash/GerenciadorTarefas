/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author tulipas
 */
public class Conexao {
    public static Connection conectar() {
        String url = "jdbc:mariadb://localhost:3306/tarefasdb"; // URL do banco
        String usuario = "tulipas";  // seu usuário do MariaDB
        String senha = "minhasenha";        // sua senha

        try {
            return DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao conectar ao banco de dados!");
        }
    }
}
