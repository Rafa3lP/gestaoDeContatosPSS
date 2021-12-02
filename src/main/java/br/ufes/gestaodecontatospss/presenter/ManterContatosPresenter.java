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
import java.util.regex.Pattern;
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

        this.view.getBtnFechar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fechar();
            }
        });

        this.view.getBtnSalvar().addActionListener(new ActionListener() {
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
        
        this.view.getBtnSalvar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update(contato);
                setVisualizar(contato);
            }
        });
        
        this.view.getBtnEditar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setEditar();
            }
        });
        
        this.view.getBtnFechar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fechar();
            }
        });
        
        setVisualizar(contato);
        
        this.view.setVisible(true);

    }

    private void setVisualizar(Contato c) {

        this.view.setTitle("Visualizar Pessoa");

        this.view.getBtnEditar().setVisible(true);
        this.view.getBtnEditar().setEnabled(true);
        this.view.getBtnSalvar().setEnabled(false);

        this.view.getTxtNome().setText(c.getNome());
        this.view.getTxtTelefone().setText(c.getTelefone());

        this.view.getTxtNome().setEditable(false);
        this.view.getTxtTelefone().setEditable(false);

    }

    private void fechar() {
        this.view.dispose();
    }

    private void salvar() {

        String nome = this.view.getTxtNome().getText();
        String telefone = this.view.getTxtTelefone().getText();

        if (nomeCorreto(nome)) {
            
            if(telefoneCorreto(telefone)) {
                
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
                
            }else{
                
                JOptionPane.showMessageDialog(
                    view,
                    "O telefone deve estar no formato\n (xx)xxxxx-xxxx ou (xx)xxxx-xxxx",
                    "Erro",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }  
            
        } else {
            JOptionPane.showMessageDialog(
                view,
                "Nome incorreto",
                "Erro",
                JOptionPane.INFORMATION_MESSAGE
            );
        }

    }

    private void setEditar() {

        this.view.setTitle("Editar Pessoa");

        this.view.getBtnEditar().setEnabled(false);
        this.view.getBtnSalvar().setEnabled(true);
        this.view.getTxtNome().setEditable(true);
        this.view.getTxtTelefone().setEditable(true);
    }

    private void update(Contato c) {
        
        this.contatoDAO = new ContatoDAO();

        String nome = this.view.getTxtNome().getText();
        String telefone = this.view.getTxtTelefone().getText();

        if (nomeCorreto(nome)) {
            
            if(telefoneCorreto(telefone)) {
                
                Contato contatoNew = new Contato(nome, telefone);

                try {
                    contatoDAO.atualizar(contatoNew, c);
                    JOptionPane.showMessageDialog(
                            view,
                            "Contato Atualizado!",
                            "Sucesso",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    
                    c.setNome(nome);
                    c.setTelefone(telefone);
                    
                } catch (SQLException ex) {

                    JOptionPane.showMessageDialog(
                            view,
                            "Erro ao atualizar contato: " + ex.getMessage(),
                            "Erro",
                            JOptionPane.ERROR_MESSAGE
                    );

                }
                
            } else {
                
                JOptionPane.showMessageDialog(
                    view,
                    "O telefone deve estar no formato\n (xx)xxxxx-xxxx ou (xx)xxxx-xxxx",
                    "Erro",
                    JOptionPane.INFORMATION_MESSAGE
                );
                                
            }
   
        } else {
            JOptionPane.showMessageDialog(
                view,
                "Nome incorreto",
                "Erro",
                JOptionPane.INFORMATION_MESSAGE
            );
            
        }
        
    }
    
    private boolean telefoneCorreto(String telefone) {
        return Pattern.matches("^\\([1-9]{2}\\)(?:[2-8]|9[1-9])[0-9]{3}\\-[0-9]{4}$", telefone);
    }
    
    private boolean nomeCorreto(String nome) {
        
        if(nome.replaceAll(" ", "").equals("")) {
            return false;
        }
        return true;
    }

}
