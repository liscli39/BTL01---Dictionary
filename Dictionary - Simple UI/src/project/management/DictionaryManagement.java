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
        File directory = new File("");
        fileName = directory.getAbsolutePath() + "\\dictionaries.txt";
       // this.insertFromFile();
        this.insertFromDatabase();
    }
    //insert word(s) into dictionary from FILE
//    public void insertFromFile(){
//        dictionary = new Dictionary();
//        try{
//            FileReader fileReader = new FileReader(fileName);        
//            BufferedReader bReader = new BufferedReader(fileReader);
//            //
//            String line = null;
//            while((line = bReader.readLine()) != null){
//                String[] word = line.split("\t");
//                //
//                Word newWord = new Word(word[0],word[1]);
//                dictionary.Add(newWord);
//            }
//            bReader.close();
//            fileReader.close();
//        }catch (FileNotFoundException ex) {
//            //
//           // System.out.println("Unable to open file '" + fileName + "'");          
//        }catch (IOException ex){
//            //
//          //  System.out.println("Error reading file '" + fileName + "'");          
//        }
//    }
    //
    public void insertFromDatabase(){
        dictionary = new Dictionary();
        SQLiteJDBCDriverConnection sql = new SQLiteJDBCDriverConnection();
        dictionary.setListWords(sql.getResult(sql.Connect()));
    }
    //
    public void dictionaryExportToFile(){
        try{
            FileWriter writer = new FileWriter(fileName);
            BufferedWriter buffer = new BufferedWriter(writer);
            //
            for (Word var : dictionary.getAllWords()) {
                buffer.write(var.getTarget()+"\t"+var.getExplain());
                buffer.newLine();
            }
            buffer.close();
        }catch (FileNotFoundException ex) {
            //
            System.out.println("Unable to open file '" + fileName + "'");          
        }catch (IOException ex){
            //
            System.out.println("Error writing file '" + fileName + "'");          
        }
    }
    //
    public Dictionary getDictionary(){
        return dictionary;
    }
    //
    public void setFileName(String file){
        fileName = file;
    }
    public String getFileName(){
        return fileName;
    }
    
    private String fileName;
    private Dictionary dictionary;
}
