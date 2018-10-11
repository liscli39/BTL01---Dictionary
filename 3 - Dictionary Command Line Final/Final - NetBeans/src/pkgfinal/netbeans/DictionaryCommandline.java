/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.netbeans;

import java.util.*;
import java.io.File;
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
    DictionaryCommandline(){
        management = new DictionaryManagement();
    }
    private void showAllWords(){
        List<Word> words =  management.getDictionary().getAllWords();
        //Print titles in order are No,English,Vietnamese
        int i = 1;
        int page = 1;
        while(i<=words.size()){
            System.out.println("--------------------------------------");
            System.out.printf("%3s %-20s %s\n","No","| English","| Vietnamese");
            System.out.println("--------------------------------------");
            //Print No, Word_target and Word_explain
            int limit = 15;
            for (Word var : words) {
                System.out.printf("%3d %-20s %s\n", i++, "| "+var.getTarget(),"| " + var.getExplain());
                limit --;
                if(limit==0) break;
            }
            //Print the separator
            System.out.println("--------------------------------------");
            System.out.println("page: "+ page+" ");
            if(i < words.size()){
                System.out.print("- Select (1)'next' (2)'exit' :");
                Scanner scanner = new Scanner(System.in);
                int c = scanner.nextInt();
                if(c==1){
                    limit = 15;
                    page ++;
                }else return;
            }
        }
    }
    private void dictionaryAdvanced(){
        System.out.println("Dictionary version Commandline 10-2018");
        System.out.println("Enter '.help' for usage hints.");
        management.insertFromFile();
        String choise = "";
        while(true){
            System.out.print("dict >> ");
            Scanner scanner = new Scanner(System.in);
            choise = scanner.nextLine().replaceAll("\\s+","");
            switch(choise){
                case ".show":
                    this.showAllWords();
                    break;
                case ".search":
                    this.dictionarySearcher();
                    break;
                case ".lookup":
                    management.dictionaryLookup();
                    break;
                case ".exit":
                    System.exit(0);
                    break;
                case ".add":
                    management.dictionaryAddWord();
                    break;
                case ".help":
                    help();
                    break;
                case ".export":
                    export();
                    break;
                default:
                    System.out.println("Error: near '"+choise+"': syntax error");
            }
        }
    }
    private void help(){
        System.out.println(".show\tShow all words in the dictionary");
        System.out.println(".search\tSearch chars and return a list words which begin by chars");
        System.out.println(".lookup\tReturn explain of this word");
        System.out.println(".add\tInsert a new word into dictionary");
        System.out.println(".help\tShow hints");
        System.out.println(".export\tExport the dictonary to the file with file path");        
        System.out.println(".exit\tTurn off the dictionary");
    }
    private void export(){
        System.out.println("File path:");
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine()+"\\dictionary_coppy.txt";
        management.dictionaryExportToFile(fileName);
    }
    private void dictionaryBasic(){
        management.insertFromCommandline();
        this.showAllWords();
    }
    private void dictionarySearcher(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("- Search for: ");
        String searchWord = scanner.nextLine().replaceAll("\\s+","");
        //
        System.out.println("- Result: ");
        List<Word> words = management.getDictionary().getAllWords();
        boolean had = false;
        //
        for (Word var : words) {
            if(this.compare(var.getTarget(),searchWord)){
                System.out.println(var.getTarget());
                had = true;
            }
        }
        //
        if(!had) System.out.println("-> Not found!!");
        //scanner.close();
    }
    private boolean compare(String s1,String s2){
        for(int i=0;i<Math.min( s1.length(), s2.length());i++){
            if( (s1.charAt(i)!=s2.charAt(i)) ) return false;
        }
        return true;
    }
    private DictionaryManagement management;
}
