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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;

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
    private TableRowSorter contatosSorter;
    
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
        
        contatosSorter = new TableRowSorter(tmContatos);
       
        
        this.view = new ListarContatosView();
        
        habilitaEdicao(false);
        
        atualizaTotal();
        
        this.tbContatos = this.view.getTblContatos();
        
        this.tbContatos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        this.tbContatos.setModel(tmContatos);
        
        this.tbContatos.setRowSorter(contatosSorter);
        
        this.tbContatos.getTableHeader().setReorderingAllowed(false);
        
        for(int i = 0; i < this.tbContatos.getColumnCount(); i++) {
            
            contatosSorter.setSortable(i, false);
            
            
        }
        
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
        
        this.view.getCbOrdenarTelefone().addItemListener(new ItemListener(){
            
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(view.getCbOrdenarTelefone().isSelected()) {
                    ordenarTelefone(true);
                }else{
                    ordenarTelefone(false);
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
                    atualizaTotal();
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
    
    private void ordenarTelefone(boolean b) {
        
        if(b) {
            List <RowSorter.SortKey> sortKeys = new ArrayList<>();
            sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
            this.contatosSorter.setSortKeys(sortKeys);
        } else {
            this.contatosSorter.setSortKeys(null);
        }
        
    }
    
    private void atualizaTotal() {
        
        this.view.getLblTotal().setText(Integer.toString(tmContatos.getRowCount()));
        
    }
    
}
