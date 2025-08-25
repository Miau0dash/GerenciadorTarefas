/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Tarefa;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tulipas
 */
public class TarefaDAO {
    private final Connection conn;

    public TarefaDAO(Connection conn) {
        this.conn = conn;
    }

    public void adicionarTarefa(Tarefa t) throws SQLException {
        String sql = "INSERT INTO tarefas (descricao, concluida) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, t.getDescricao());
            stmt.setBoolean(2, t.isConcluida());
            stmt.executeUpdate();
        }
    }

    public List<Tarefa> listarTarefas() throws SQLException {
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM tarefas";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while(rs.next()) {
                tarefas.add(new Tarefa(rs.getInt("id"), rs.getString("descricao"), rs.getBoolean("concluida")));
            }
        }
        return tarefas;
    }

    public void atualizarTarefa(Tarefa t) throws SQLException {
        String sql = "UPDATE tarefas SET descricao=?, concluida=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, t.getDescricao());
            stmt.setBoolean(2, t.isConcluida());
            stmt.setInt(3, t.getId());
            stmt.executeUpdate();
        }
    }
    
    public void deletarTarefa(int id) throws SQLException {
    String sql = "DELETE FROM tarefas WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}

