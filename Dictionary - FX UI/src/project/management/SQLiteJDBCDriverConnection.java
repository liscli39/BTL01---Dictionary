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
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            return words;
        }
    }
    public List<Word> getResult(Connection connection,String sQuery){
        List<Word> words = new LinkedList<>();
        String sCon = "select idx,word,detail from tbl_edict where word like ? order by word asc limit 10";
        
        try{
            PreparedStatement stmt  = connection.prepareStatement(sCon);
            stmt.setString(1,sQuery+"%");
            ResultSet rs = stmt.executeQuery();
            // loop through the result set
            while (rs.next()) {
                Word w = new Word(rs.getInt("idx"),rs.getString("word"),rs.getString("detail"));
                words.add(w);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            return words;
        }
    }
    
    public void insertIntoDatabase(Connection connection,Word word){
        String sql = "INSERT INTO tbl_edict(word,detail) VALUES(?,?)";
 
        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1, word.getTarget());
            pstmt.setString(2, word.getExplain());
            pstmt.executeUpdate();
            System.out.println("insert successfull!!");
        } catch (SQLException e) {
//            System.out.println(e.getMessage());
        }
    }
    public void updateInDatabase(Connection connection,Word word){        
        String sql = "UPDATE tbl_edict SET word = ? , "
                + "detail = ? "
                + "WHERE idx = ?";
 
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
 
            // set the corresponding param
            pstmt.setString(1, word.getTarget());
            pstmt.setString(2, word.getExplain());
            pstmt.setInt(3, word.getId());
            // update 
            pstmt.executeUpdate();
//            System.out.println("update successfull!!");
        } catch (SQLException e) {
//            System.out.println(e.getMessage());
        }
    }
    public void deleteFromDatabase(Connection connection,int id){        
        String sql = "DELETE FROM tbl_edict WHERE idx = ?";
//        System.out.println(id);
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setInt(1, id);
            // execute the delete statement
            pstmt.executeUpdate();
//            System.out.println("delete success!");
        } catch (SQLException e) {
//            System.out.println(e.getMessage());
        }
    }
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
