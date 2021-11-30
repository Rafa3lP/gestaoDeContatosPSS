/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.ufes.gestaodecontatospss.view;

import br.ufes.gestaodecontatospss.collection.ContatoCollection;
import br.ufes.gestaodecontatospss.presenter.IncluirPessoasPresenter;
import br.ufes.gestaodecontatospss.presenter.ListarContatosPresenter;

/**
 *
 * @author Rafael e Heflain
 */
public class PrincipalView extends javax.swing.JFrame {
    private ContatoCollection contatos;
    /**
     * 
     * @param contatos 
     */
    public PrincipalView(ContatoCollection contatos) {
        
        this.contatos = contatos;
        
        initComponents();
        this.setLocationRelativeTo(this.getParent());
        this.setExtendedState(MAXIMIZED_BOTH);
        
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mbMenuPrincipal = new javax.swing.JMenuBar();
        bntOpcoes = new javax.swing.JMenu();
        btnIncluirContato = new javax.swing.JMenuItem();
        btnListagemDePessoas = new javax.swing.JMenuItem();
        btnJanela = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastro de pessoas");

        bntOpcoes.setText("Opções");

        btnIncluirContato.setText("Incluir contato");
        btnIncluirContato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirContatoActionPerformed(evt);
            }
        });
        bntOpcoes.add(btnIncluirContato);

        btnListagemDePessoas.setText("Listagem de pessoas");
        btnListagemDePessoas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListagemDePessoasActionPerformed(evt);
            }
        });
        bntOpcoes.add(btnListagemDePessoas);

        mbMenuPrincipal.add(bntOpcoes);

        btnJanela.setText("Janela");
        mbMenuPrincipal.add(btnJanela);

        setJMenuBar(mbMenuPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 278, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIncluirContatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirContatoActionPerformed
        IncluirPessoasPresenter presenter = new IncluirPessoasPresenter(contatos);
    }//GEN-LAST:event_btnIncluirContatoActionPerformed

    private void btnListagemDePessoasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListagemDePessoasActionPerformed
        ListarContatosPresenter presenter = new ListarContatosPresenter(contatos);
    }//GEN-LAST:event_btnListagemDePessoasActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu bntOpcoes;
    private javax.swing.JMenuItem btnIncluirContato;
    private javax.swing.JMenu btnJanela;
    private javax.swing.JMenuItem btnListagemDePessoas;
    private javax.swing.JMenuBar mbMenuPrincipal;
    // End of variables declaration//GEN-END:variables
}
