/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slideruploader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.pool.OracleDataSource;
/**
 *
 * @author Corailla20
 */
public class OracleConnector {
        
    private static OracleConnector instance;
    private OracleDataSource source;
    public OracleConnector(){
        try {
            source = new OracleDataSource();
            source.setUser("SYS as sysdba");
            source.setServerName("******");
            source.setDatabaseName("******");
            source.setPassword("*****");
            source.setDriverType("thin");
            source.setPortNumber(1521);
            source.setURL("jdbc:oracle:thin:@***.***.***.***:1521/orcl");
        } 
        catch (SQLException ex) {
            Logger.getLogger(OracleConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Accès à l'instance du connecteur
     * @return instance du connecteur
     */
    public static OracleConnector getInstance(){
        if (instance == null) {
            instance = new OracleConnector();
        }
        return instance;
    }
    
    /**
     * Permet d'exécuter une requête SELECT
     * @param retour Liste contenant les lignes ramenées par la requête
     * @param sql Requete à exécuter
     * @throws SQLException 
     */
    public void execSelect(List<List<Object>> retour,String sql) throws SQLException {
        try (Connection connexion = source.getConnection()) {
            try (Statement stm = connexion.createStatement()) {
                try (ResultSet rs = stm.executeQuery(sql)) {
                    while (rs.next()) {
                        ArrayList<Object> uneLigne = new ArrayList<>();
                        for (int i=1;i<=rs.getMetaData().getColumnCount();i++) {
                            uneLigne.add(rs.getObject(i)==null?"":rs.getObject(i));
                        }
                        retour.add(Collections.unmodifiableList(uneLigne));
                    }
                }
            }
        } 
    }
    
    /**
     * Permet d'exécuter une requête autre que SELECT
     * @param sql requête à exécuter
     * @return Nombre de lignes impactées
     * @throws SQLException 
     */
    public int exec(String sql) throws SQLException {
        try (Connection connexion = source.getConnection()) {
            connexion.setAutoCommit(true);
            try (Statement stm = connexion.createStatement()) {   
                return stm.executeUpdate(sql);
            }
        } 
    }
    
    public ArrayList<String> execSelectColonne(String sql) throws SQLException{
        ArrayList<String> result = new ArrayList<String>();
        try (Connection connexion = source.getConnection()) {
            try(Statement stmt = connexion.createStatement()){     
                ResultSet rs = stmt.executeQuery(sql);
                int columnCount = rs.getMetaData().getColumnCount();
                while(rs.next())
                {
                    result.add(rs.getString(1));
                }
            }
        }
        return result;
    }
    
    public ArrayList<String> execSelectLigne(String sql) throws SQLException{
        ArrayList<String> result = new ArrayList<String>();
        try (Connection connexion = source.getConnection()) {
            try (Statement stm = connexion.createStatement()) {
                try (ResultSet rs = stm.executeQuery(sql)) {
                    rs.next();
                    for (int i=1;i<=rs.getMetaData().getColumnCount();i++) {
                            result.add(rs.getString(i));
                    }
                }
            }
        } 
        return result;
    }
    
    public String execSelectUniqueString(String sql) throws SQLException{
        String result = "";
        try (Connection connexion = source.getConnection()) {
            try(Statement stmt = connexion.createStatement()){     
                ResultSet rs = stmt.executeQuery(sql);
                rs.next();
                result = rs.getString(1);
            }
        }
        return result;
    }
}
