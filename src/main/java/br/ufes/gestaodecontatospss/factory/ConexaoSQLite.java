/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.gestaodecontatospss.factory;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Rafael e Heflain
 */
public class ConexaoSQLite {
    
    private static final String DB_URL = "jdbc:sqlite:db/contatos.db";
    
    public static Connection conectar() throws SQLException {
        
        return DriverManager.getConnection(DB_URL);
        
    }
    
    public static void checkDiretorioDb() {
        File diretorio = new File("db/");
        if (!diretorio.exists()){
            diretorio.mkdirs();
        }
    }
    
}
