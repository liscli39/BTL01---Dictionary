/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dcl.simply.netbeans;

import java.util.List;
/**
 *
 * @author Liscli
 */
public class DictionaryCommandline {
    private Dictionary dictionary;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DictionaryCommandline dc = new DictionaryCommandline();
        dc.dictionaryBasic();
    }
        private void showAllWords(){
        List<Word> words = dictionary.getAllWords();
        //
        int i = 1;
        System.out.println("No\t |English\t |Vietnamese");
        for (Word var : words) {
            System.out.printf("%d\t |%s\t |%s \n", i, var.getTarget(), var.getExplain());
            i++;
        }
    }

    private void dictionaryBasic(){
        dictionary = new DictionaryManagement().insertFromCommandline();
        this.showAllWords();
    }
}
