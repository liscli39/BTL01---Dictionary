/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.management;

import java.io.*;
import project.Entity.Dictionary;
import project.Entity.Word;
import java.sql.ResultSet;

/**
 *
 * @author Liscli
 */
public class DictionaryManagement {
    public DictionaryManagement(){
       this.getListWordsWithQuery("zzzzzzzzzzz!");
//        this.insertFromDatabase();
 //      this.getListWordsWithQuery("select word,detail from tbl_edict");
    }
    public void insertFromDatabase(){
        
        dictionary = new Dictionary();
        sql = new SQLiteJDBCDriverConnection();
        dictionary.setListWords(sql.getResult(sql.Connect()));
    }
    public void getListWordsWithQuery(String sQuery){
        dictionary = new Dictionary();
        sql = new SQLiteJDBCDriverConnection();
        dictionary.setListWords(sql.getResult(sql.Connect(),sQuery));
        sql.CloseConnection();
    }
    
    public void Add(Word w){
        
    }
    public void Edit(Word w){
        
    }
    public void Delete(int idx){
        
    }
    
    public Dictionary getDictionary(){
        return dictionary;
    }
    public void setFileName(String file){
        fileName = file;
    }
    public String getFileName(){
        return fileName;
    }
    
    private SQLiteJDBCDriverConnection sql;
    private String fileName;
    private Dictionary dictionary;
}
