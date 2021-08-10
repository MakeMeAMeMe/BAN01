/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FQA
 */
public class Conexao {

    private static Conexao conexaoSingleton = null;
    private Connection conn;

    private Conexao() {
        String driver = "org.postgresql.Driver";
        String user = "Usuario";
        String senha = "SenhaUsuario";
        String url = "jdbc:postgresql://localhost:5432/esportes";

        try {
            Class.forName(driver);
            this.conn = (Connection) DriverManager.getConnection(url, user, senha);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
    }

    public static Conexao getInstance() {
        if (conexaoSingleton == null) {
            conexaoSingleton = new Conexao();
        }
        return conexaoSingleton;
    }

    public Connection getConnection() {
        return this.conn;
    }

    public void closeConnection() {
        try {
            this.conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
    }
}
