/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.gestaodecontatospss.presenter;

import br.ufes.gestaodecontatospss.dao.ContatoDAO;
import br.ufes.gestaodecontatospss.model.Contato;
import br.ufes.gestaodecontatospss.view.IncluirPessoasView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Heflain
 */
public class IncluirPessoasPresenter {
    
    private IncluirPessoasView view;
    private ContatoDAO contatoDAO;
    
    public IncluirPessoasPresenter() {
        
        this.contatoDAO = new ContatoDAO();
        
        this.view = new IncluirPessoasView();
        
        this.view.getBtnFechar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Fechar();
            }
        });
        
        this.view.getBtnSalvar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Salvar();
            }
        });
        
        this.view.setVisible(true);   
    }
    
    private void Fechar() {
        this.view.dispose();
    }
    
    private void Salvar() {
        
        String nome = this.view.getTxtNome().getText();
        String telefone = this.view.getTxtTelefone().getText();

        Contato contato = new Contato(nome, telefone);
        
        try {
    
            contatoDAO.inserir(contato);
            JOptionPane.showMessageDialog(
                view, 
                "Contato " + contato.getNome() + " salvo com sucesso ",
                "Salvo com sucesso",
                JOptionPane.INFORMATION_MESSAGE
            );
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                view, 
                "Erro ao inserir contato: " + ex.getMessage(),
                "Salvo com sucesso",
                JOptionPane.ERROR_MESSAGE
            );
        } finally {
            this.view.setTxtNome("");
            this.view.setTxtTelefone("");
            this.view.requestFocus();
        }
        
    }
    
}
