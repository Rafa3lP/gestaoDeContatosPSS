/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.gestaodecontatospss.dao;

import br.ufes.gestaodecontatospss.factory.ConexaoSQLite;
import br.ufes.gestaodecontatospss.model.Contato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rafael e Heflain
 */
public class ContatoDAO {
    
    public static void criaTContatos() throws SQLException {
        
        String sql = "CREATE TABLE IF NOT EXISTS contatos("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "nome VARCHAR NOT NULL UNIQUE, "
                + "telefone VARCHAR NOT NULL UNIQUE"
                + ")";
        
        Connection conexao = ConexaoSQLite.conectar();
        Statement st = conexao.createStatement();
        st.execute(sql);
        
        st.close();
        conexao.close();
        
    }
    
    public void inserir(Contato contato) throws SQLException {
        
        String sql = "INSERT INTO contatos(nome, telefone) "
                + "VALUES(?, ?)";
        Connection conexao = ConexaoSQLite.conectar();
        PreparedStatement pstm = conexao.prepareStatement(sql);
        
        pstm.setString(1, contato.getNome());
        
        pstm.setString(2, contato.getTelefone());
        
        pstm.execute();
        
        pstm.close();
        conexao.close();
        
    }
    
    public void excluir(Contato contato) throws SQLException {
        
        String sql = "DELETE FROM contatos "
                + "WHERE "
                + "nome = ? "
                + "AND "
                + "telefone = ?";
        
        Connection conexao = ConexaoSQLite.conectar();
        PreparedStatement pstm = conexao.prepareStatement(sql);
        
        pstm.setString(1, contato.getNome());
        pstm.setString(2, contato.getTelefone());
        
        pstm.execute();
        
        pstm.close();
        conexao.close();
        
    }
    
    public void atualizar(Contato contatoNew, Contato contatoOld) throws SQLException {
        
        String sql = "UPDATE contatos "
                + "SET nome = ?, "
                + "telefone = ? "
                + "WHERE "
                + "nome = ? "
                + "AND "
                + "telefone = ?";
        
        Connection conexao = ConexaoSQLite.conectar();
        PreparedStatement pstm = conexao.prepareStatement(sql);
        
        pstm.setString(1, contatoNew.getNome());
        pstm.setString(2, contatoNew.getTelefone());
        pstm.setString(3, contatoOld.getNome());
        pstm.setString(4, contatoOld.getTelefone());
        
        pstm.execute();
        
        pstm.close();
        conexao.close();
        
    }
    
    public List<Contato> getContatos() throws SQLException {
        
        List<Contato> contatos = new ArrayList<>();
        String sql = "SELECT * FROM contatos";
        
        Connection conexao = ConexaoSQLite.conectar();
        Statement st = conexao.createStatement();
        ResultSet rSet = st.executeQuery(sql);
        
        while(rSet.next()) {
            
            Contato contato = new Contato(
                    rSet.getString("nome"),
                    rSet.getString("telefone")
            );
            
            contatos.add(contato);
            
        }
        
        st.close();
        conexao.close();
        
        return contatos;
        
    }
    
}
