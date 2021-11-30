/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.gestaodecontatospss.presenter;

import br.ufes.gestaodecontatospss.collection.ContatoCollection;
import br.ufes.gestaodecontatospss.model.Contato;
import br.ufes.gestaodecontatospss.view.ListarContatosView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ListIterator;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rafael e Heflain
 */
public class ListarContatosPresenter {
    private ListarContatosView view;
    private DefaultTableModel tmTable;
    private ContatoCollection contatos;
    
    public ListarContatosPresenter(ContatoCollection contatos){
        
        Contato contato;
        
        this.contatos = contatos;
        this.view = new ListarContatosView();
        this.tmTable = new DefaultTableModel(new Object[][]{}, new String[]{"Nome", "Telefone"});
        
        this.tmTable.setNumRows(0);
        
        this.view.getTblContatos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        ListIterator<Contato> it = this.contatos.getContatos().listIterator();
        
        while(it.hasNext()){
            contato = it.next();
            tmTable.addRow(new Object[]{contato.getNome(), contato.getTelefone()});
    }
        
        this.view.getTblContatos().setModel(tmTable);
                
        this.view.getBtnVisualizar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Visualizar();
            }
        });
        
        this.view.getBtnExcluir().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Excluir();
            }
        });
        
        this.view.getBtnFechar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Fechar();
            }
        });
        
        this.view.setVisible(true);
    }
    
    private void Visualizar(){
        
    }
    
    private void Excluir(){
        /*Heflain - Não está funcionando*/
       /*((DefaultTableModel) this.view.getModel()).removeRow(this.view.getSelectedRow());*/
    }
    
    private void Fechar(){
        this.view.dispose();
    }
}
