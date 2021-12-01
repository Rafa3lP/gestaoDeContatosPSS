/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.gestaodecontatospss.presenter;

import br.ufes.gestaodecontatospss.dao.ContatoDAO;
import br.ufes.gestaodecontatospss.model.Contato;
import br.ufes.gestaodecontatospss.view.ManterContatosView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Heflain
 */
public class ManterContatosPresenter {
    
    private ManterContatosView view;
    private ContatoDAO contatoDAO;
    
    public ManterContatosPresenter() {
        
        this.contatoDAO = new ContatoDAO();
        
        this.view = new ManterContatosView();
        
        this.view.getBtnEditar().setVisible(false);
        
        this.view.getBtnFechar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                fechar();
            }
        });
        
        this.view.getBtnSalvar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                salvar();
            }
        });
        
        this.view.setVisible(true);   
    }
    
    public ManterContatosPresenter(Contato contato) {
        //modo de visualização
        this.view = new ManterContatosView();
        this.view.getBtnFechar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                fechar();
            }
        });
        visualizar(contato);
        this.view.setVisible(true);
        
    }
    
    private void visualizar(Contato c) {
        
        this.view.setTitle("Visualizar Pessoa");
        
        this.view.getBtnEditar().setVisible(true);
        this.view.getBtnSalvar().setEnabled(false);
        
        this.view.getTxtNome().setText(c.getNome());
        this.view.getTxtTelefone().setText(c.getTelefone());
        
        this.view.getTxtNome().setEditable(false);
        this.view.getTxtTelefone().setEditable(false);
        
        this.view.getBtnEditar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                editar(c);
            }
        });
        
    }
    
    private void fechar() {
        this.view.dispose();
    }
    
    private void salvar() {
        
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
            this.view.getTxtNome().setText("");
            this.view.getTxtTelefone().setText("");
            this.view.requestFocus();
        }
        
    }
    
    private void editar(Contato c) {
        
        this.view.setTitle("Editar Pessoa");
        
        this.view.getBtnEditar().setEnabled(false);
        this.view.getBtnSalvar().setEnabled(true);
        this.view.getTxtNome().setEditable(true);
        this.view.getTxtTelefone().setEditable(true);
        
        this.view.getBtnSalvar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                update(c);
            }
        });
        
    }
    
    private void update(Contato c) {
        
        this.contatoDAO = new ContatoDAO();
        
        String nome = this.view.getTxtNome().getText();
        String telefone = this.view.getTxtTelefone().getText();

        Contato contatoNew = new Contato(nome, telefone);
        
        try {
            contatoDAO.atualizar(contatoNew, c);
            JOptionPane.showMessageDialog(
                view, 
                "Contato Atualizado!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE
            );
            visualizar(contatoNew);
        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(
                view, 
                "Erro ao atualizar contato: " + ex.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
            
            visualizar(c);
            
        }
        
    }
    
}
