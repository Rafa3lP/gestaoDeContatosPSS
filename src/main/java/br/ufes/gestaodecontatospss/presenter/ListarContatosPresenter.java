/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.gestaodecontatospss.presenter;

import br.ufes.gestaodecontatospss.dao.ContatoDAO;
import br.ufes.gestaodecontatospss.model.Contato;
import br.ufes.gestaodecontatospss.model.ContatoTableModel;
import br.ufes.gestaodecontatospss.view.ListarContatosView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Rafael e Heflain
 */
public class ListarContatosPresenter {
    
    private ListarContatosView view;
    private ContatoTableModel tmContatos;
    private JTable tbContatos;
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
        
        tmContatos = new ContatoTableModel(contatos);
        
        this.view = new ListarContatosView();
        
        habilitaEdicao(false);
        
        this.tbContatos = this.view.getTblContatos();
        
        this.tbContatos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        this.tbContatos.setModel(tmContatos);
        
        this.tbContatos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            
            @Override
            public void valueChanged(ListSelectionEvent e) {
                
                if(tbContatos.getSelectedRow() != -1) {
                    habilitaEdicao(true);
                }else{
                    habilitaEdicao(false);
                }
                
            }   
        
        });
        
        this.view.getBtnVisualizar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int linha = tbContatos.getSelectedRow();
                visualizar(linha);
            }
        });
        
        this.view.getBtnExcluir().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int linha = tbContatos.getSelectedRow();
                excluir(linha);
            }
        });
        
        this.view.getBtnFechar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                fechar();
            }
        });
        
        this.view.setVisible(true);
    }
    
    private void visualizar(int linha){
        
        Contato c = this.tmContatos.getContato(linha);
        new ManterContatosPresenter(c);
        this.view.dispose();
        
    }
    
    private void excluir(int linha) {
 
        Contato c = this.tmContatos.getContato(linha);
        int r = JOptionPane.showConfirmDialog(
            this.view, 
            "Deseja realmente excluir o contato " + c.getNome() + "?", "Confirmação", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );
        try{
            switch(r) {
                case 0:
                    this.contatoDAO.excluir(c);
                    this.tmContatos.excluirContato(linha);
                    break;
                case 1:
                default:
                    return;
            }
            
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(
                this.view,
                "Erro ao excluir contato: " + ex.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }
        
    }
    
    private void fechar(){
        this.view.dispose();
    }
    
    private void habilitaEdicao(boolean habilitado) {
        
        if(habilitado) {
            this.view.getBtnVisualizar().setEnabled(true);
            this.view.getBtnExcluir().setEnabled(true);
        }else{
            this.view.getBtnVisualizar().setEnabled(false);
            this.view.getBtnExcluir().setEnabled(false);
        }
        
    }
    
}
