/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author tulipas
 */
public class Tarefa {
     private int id;
    private String descricao;
    private boolean concluida;

    public Tarefa(int id, String descricao, boolean concluida) {
        this.id = id;
        this.descricao = descricao;
        this.concluida = concluida;
    }

    public Tarefa(String descricao) {
        this.descricao = descricao;
        this.concluida = false;
    }

    public int getId() { return id; }
    public String getDescricao() { return descricao; }
    public boolean isConcluida() { return concluida; }

    public void setId(int id) { this.id = id; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setConcluida(boolean concluida) { this.concluida = concluida; }
}
