/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.gestaodecontatospss.model;

/**
 *
 * @author Rafael
 */
public class Contato{
    private String nome;
    private String Telefone;

    public Contato(String nome, String Telefone) {
        this.nome = nome;
        this.Telefone = Telefone;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String Telefone) {
        this.Telefone = Telefone;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return Telefone;
    }
    
    @Override
    public String toString() {
        
        return "Contato: " + this.nome + " tel: " + this.Telefone;
        
    }
    
}

