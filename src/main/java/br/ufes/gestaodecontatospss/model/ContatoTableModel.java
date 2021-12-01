/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.gestaodecontatospss.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Rafael e Heflain
 */
public class ContatoTableModel extends AbstractTableModel{
    
    private List<Contato> contatos;
    private String[] colunas = new String[]{
            "Nome", "Telefone"
            };
    
    public ContatoTableModel(List<Contato> contatos) {
        
        this.contatos = contatos;
        
    }
    
    public ContatoTableModel() {
        
        this.contatos = new ArrayList<>();
        
    }
    
    @Override
    public int getRowCount() {
        return contatos.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        Contato contatoSelecionado = contatos.get(rowIndex);
        String valueObject = null;
        switch(columnIndex){
            case 0: valueObject = contatoSelecionado.getNome(); break;
            case 1 : valueObject = contatoSelecionado.getTelefone(); break;
            default: System.err.println("Índice inválido");
        }

        return valueObject;
        
    }
    
    @Override
    public String getColumnName(int columnIndex){
      return colunas[columnIndex];
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
      Contato contato = contatos.get(rowIndex);

        switch (columnIndex) {
            case 0:
                contato.setNome(aValue.toString());
                break;
            case 1:
                contato.setTelefone(aValue.toString());
                break;
            default:
                System.err.println("Índice da coluna inválido");
        }
     
        fireTableCellUpdated(rowIndex, columnIndex);
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }


    public Contato getContato(int rowIndex) {
        return contatos.get(rowIndex);
    }
    
    public void excluirContato(int rowIndex) {
        contatos.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
    
}
