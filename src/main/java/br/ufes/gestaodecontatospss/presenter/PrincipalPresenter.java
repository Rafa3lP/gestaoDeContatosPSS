/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.gestaodecontatospss.presenter;

import br.ufes.gestaodecontatospss.dao.ContatoDAO;
import br.ufes.gestaodecontatospss.view.PrincipalView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Rafael e Heflain
 */
public class PrincipalPresenter {
    
    private static PrincipalView view;
    
    public static void main(String[] args){
        view = new PrincipalView();
        iniciar();
    }
    
    private static void iniciar() {
        try {
            
            ContatoDAO.criaTContatos();
            
        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(
                    view, 
                    ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
            
            view.dispose();
            System.exit(1);
            
        }
        
        view.getBtnFechar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fechar();
            }
        
        });
        
        view.getBtnListarContatos().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListarContatosPresenter();
            }
            
        });
        
        view.getBtnNovoContato().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManterContatosPresenter();
            }
            
        });
        
        view.setVisible(true);
    }
    
    private static void fechar() {
        
        view.dispose();
        System.exit(0);
        
    }
    
}
