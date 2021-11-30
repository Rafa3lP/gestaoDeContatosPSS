/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.gestaodecontatospss.presenter;

import br.ufes.gestaodecontatospss.dao.ContatoDAO;
import br.ufes.gestaodecontatospss.model.Contato;
import br.ufes.gestaodecontatospss.view.ListarContatosView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rafael e Heflain
 */
public class ListarContatosPresenter {
    
    private ListarContatosView view;
    private DefaultTableModel tmTable;
    private List<Contato> contatos;
    private ContatoDAO contatoDAO;
    
    public ListarContatosPresenter() {
        
        this.contatoDAO = new ContatoDAO();
        
        try{
            
            this.contatos = contatoDAO.getContatos();
            
        } catch(SQLException ex) {
            
            JOptionPane.showMessageDialog(
                view,
                "Falha ao consultar contatos: " + ex.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
            
        }
        
        this.view = new ListarContatosView();
        this.tmTable = new DefaultTableModel(new Object[][]{}, new String[]{"Nome", "Telefone"});
        this.tmTable.setNumRows(0);
        
        this.view.getTblContatos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        for(Contato c: this.contatos) {
            tmTable.addRow(new Object[]{c.getNome(), c.getTelefone()});
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
