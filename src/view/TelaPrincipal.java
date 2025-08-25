/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import dao.Conexao;
import model.Tarefa;
import dao.TarefaDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author tulipas
 */
public class TelaPrincipal extends JFrame {
     private TarefaDAO tarefaDAO;
    private JTable tabela;
    private DefaultTableModel modelo;

    public TelaPrincipal() {
        setTitle("Gerenciador de Tarefas");
        setSize(650, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null); // centraliza a janela
         // Conexão com o banco
         Connection conn = Conexao.conectar();
         tarefaDAO = new TarefaDAO(conn);
        // Modelo e tabela
        modelo = new DefaultTableModel(new Object[]{"ID", "Descrição", "Concluída"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // impede edição direta
            }
        };
        tabela = new JTable(modelo);
        tabela.setFont(new Font("Arial", Font.PLAIN, 14));
        tabela.setRowHeight(25);

        // Renderer para colorir tarefas concluídas
        tabela.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                Boolean concluida = (Boolean) table.getValueAt(row, 2);
                if (concluida) {
                    c.setBackground(new Color(200, 255, 200)); // verde claro
                    c.setForeground(Color.DARK_GRAY);
                } else {
                    c.setBackground(Color.WHITE);
                    c.setForeground(Color.BLACK);
                }
                if (isSelected) {
                    c.setBackground(new Color(173, 216, 230)); // azul claro quando selecionada
                }
                return c;
            }
        });

        add(new JScrollPane(tabela), BorderLayout.CENTER);

        // Painel de botões
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));

        JButton btnAdd = new JButton("Adicionar Tarefa");
        btnAdd.setBackground(new Color(135, 206, 250));
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Arial", Font.BOLD, 14));

        JButton btnConcluir = new JButton("Concluir Tarefa");
        btnConcluir.setBackground(new Color(144, 238, 144));
        btnConcluir.setForeground(Color.BLACK);
        btnConcluir.setFont(new Font("Arial", Font.BOLD, 14));

        JButton btnExcluir = new JButton("Excluir Tarefa");
        btnExcluir.setBackground(new Color(250, 128, 114));
        btnExcluir.setForeground(Color.WHITE);
        btnExcluir.setFont(new Font("Arial", Font.BOLD, 14));

        btnAdd.addActionListener(e -> adicionarTarefa());
        btnConcluir.addActionListener(e -> concluirTarefa());
        btnExcluir.addActionListener(e -> excluirTarefa());

        painelBotoes.add(btnAdd);
        painelBotoes.add(btnConcluir);
        painelBotoes.add(btnExcluir);

        add(painelBotoes, BorderLayout.SOUTH);

        atualizarTabela();
        setVisible(true);
    }

    private void adicionarTarefa() {
        String descricao = JOptionPane.showInputDialog("Descrição da tarefa:");
        if (descricao != null && !descricao.trim().isEmpty()) {
            try {
                tarefaDAO.adicionarTarefa(new Tarefa(descricao));
                atualizarTabela();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void concluirTarefa() {
        int linha = tabela.getSelectedRow();
        if (linha != -1) {
            int id = (int) modelo.getValueAt(linha, 0);
            try {
                Tarefa t = new Tarefa(id, (String) modelo.getValueAt(linha, 1), true);
                tarefaDAO.atualizarTarefa(t);
                atualizarTabela();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma tarefa para concluir!");
        }
    }

    private void excluirTarefa() {
        int linha = tabela.getSelectedRow();
        if (linha != -1) {
            int id = (int) modelo.getValueAt(linha, 0);
            try {
                tarefaDAO.deletarTarefa(id);
                atualizarTabela();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma tarefa para excluir!");
        }
    }

    private void atualizarTabela() {
        try {
            List<Tarefa> tarefas = tarefaDAO.listarTarefas();
            modelo.setRowCount(0);
            for (Tarefa t : tarefas) {
                modelo.addRow(new Object[]{t.getId(), t.getDescricao(), t.isConcluida()});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
