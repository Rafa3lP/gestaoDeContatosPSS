/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.gestaodecontatospss.collection;

import br.ufes.gestaodecontatospss.model.Contato;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Rafael e Heflain
 */
public class ContatoCollection {

    private LinkedList<Contato> contatos;

    public ContatoCollection() {
        contatos = new LinkedList();
    }

    public List<Contato> getContatos() {
        return Collections.unmodifiableList(contatos);
    }
    
    public void add(Contato contato){
        contatos.add(contato);
    }

    public void ordenaTelefone(){
        Collections.sort(contatos, new Comparator<Contato>() {
            @Override 
            public int compare(Contato objetoUm, Contato objetoDois) {
                String string1 = objetoUm.getTelefone();
                String string2 = objetoDois.getTelefone();
                return string1.compareTo(string2);
            }
        });
    }
    
    public void ordenaNome(){
        Collections.sort(contatos, new Comparator<Contato>() {
            @Override 
            public int compare(Contato objetoUm, Contato objetoDois) {
                String string1 = objetoUm.getNome();
                String string2 = objetoDois.getNome();
                return string1.compareTo(string2);
            }
        });
    }
    
}
