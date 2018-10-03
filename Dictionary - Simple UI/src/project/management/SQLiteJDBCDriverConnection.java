/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.management;

import java.util.*;
import java.io.File;
import java.sql.*;
import project.Entity.Word;
/**
 *
 * @author Liscli
 */
public class SQLiteJDBCDriverConnection {
    public Connection Connect() {
        conn = null;
        try {
            // db parameters
            File directory = new File("");
            String dbUrl = directory.getAbsolutePath() + "\\evdict.db";
            String url = "jdbc:sqlite:" + dbUrl;
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            //System.out.println("Connection to SQLite has been established.");
            
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
        }
        return conn;
    }
    //
    public List<Word> getResult(Connection connection){
        List<Word> words = new LinkedList<>();
        String sCon = "select word,detail from tbl_edict";
        try (Statement stmt  = connection.createStatement();
             ResultSet rs    = stmt.executeQuery(sCon)){
            // loop through the result set
            int id = 0;
            while (rs.next()) {
                Word w = new Word(id++,rs.getString("word"),rs.getString("detail"));
                words.add(w);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            return words;
        }
    }
    //
    public void CloseConnection(){
        try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
               // System.out.println(ex.getMessage());
            }
    }
    private Connection conn;
}
