/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.gestaodecontatospss.presenter;

import br.ufes.gestaodecontatospss.collection.ContatoCollection;
import br.ufes.gestaodecontatospss.view.PrincipalView;

/**
 *
 * @author Rafael e Heflain
 */
public class PrincipalPresenter {
    public static void main(String[] args){
        ContatoCollection contatos = new ContatoCollection();
        PrincipalView principalView = new PrincipalView(contatos);
    }
}
