/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.gestaodecontatospss.presenter;

import br.ufes.gestaodecontatospss.view.IncluirPessoasView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Heflain
 */
public class IncluirPessoasPresenter {
    private IncluirPessoasView view;
    
    public IncluirPessoasPresenter(){
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
    
    private void Fechar(){
        this.view.dispose();
    }
    
    private void Salvar(){
        String nome = this.view.getTxtNome().getText();
        String Telefone = this.view.getTxtTelefone().getText();

    }
}
