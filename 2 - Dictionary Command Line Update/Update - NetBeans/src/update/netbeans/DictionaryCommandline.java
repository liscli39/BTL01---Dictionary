/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package update.netbeans;

import java.util.List;
/**
 *
 * @author Liscli
 */
public class DictionaryCommandline {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DictionaryCommandline dc = new DictionaryCommandline();
        dc.dictionaryAdvanced();
    }
    private void showAllWords(){
        List<Word> words = dictionary.getAllWords();
        //
        int i = 1;
        System.out.println("--------------------------------------");
        System.out.printf("%3s %-20s %s\n","No","| English","| Vietnamese");
        System.out.println("--------------------------------------");
        //
        for (Word var : words) {
            System.out.printf("%3d %-20s %s\n", i, "| "+var.getTarget(),"| " + var.getExplain());
            i++;
        }
        //
        System.out.println("--------------------------------------");
    }
    private void dictionaryAdvanced(){
        DictionaryManagement management = new DictionaryManagement();
        dictionary = management.insertFromFile();
        this.showAllWords();
        management.dictionaryLookup();
    } 
    private void dictionaryBasic(){
        dictionary = new DictionaryManagement().insertFromCommandline();
        this.showAllWords();
    }
    
    private Dictionary dictionary;
}
