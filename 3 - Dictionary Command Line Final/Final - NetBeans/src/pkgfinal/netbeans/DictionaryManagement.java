/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.netbeans;

import java.util.*;
import java.io.*;
/**
 *
 * @author Liscli
 */
public class DictionaryManagement {
    DictionaryManagement(){
        File directory = new File("");
        fileName = "dictionaries.txt";
        
    }
    //insert word(s) into dictionary from COMMANDLINE
    public void insertFromCommandline(){
        dictionary = new Dictionary();
        Scanner scanner = new Scanner(System.in); //Create a Scanner object
        int numWors = scanner.nextInt();
        scanner.nextLine(); 
        //
        for(int i=0;i<numWors;i++){
            Word newWord = new Word(scanner.nextLine(),scanner.nextLine());
            dictionary.Add(newWord);
        }
        scanner.close();
    }
    //insert word(s) into dictionary from FILE
    public void insertFromFile(){
        dictionary = new Dictionary();
        try{
            FileReader fileReader = new FileReader(fileName);        
            BufferedReader bReader = new BufferedReader(fileReader);
            //
            String line = null;
            while((line = bReader.readLine()) != null){
                String[] word = line.split("\t");
                //
                Word newWord = new Word(word[0],word[1]);
                dictionary.Add(newWord);
            }
            bReader.close();
            fileReader.close();
        }catch (FileNotFoundException ex) {
            //
            System.out.println("Unable to open file '" + fileName + "'");          
        }catch (IOException ex){
            //
            System.out.println("Error reading file '" + fileName + "'");          
        }
    }
    //
    public void dictionaryExportToFile(String fileName){
        try{
            System.out.println(fileName);
            FileWriter writer = new FileWriter(fileName);
            BufferedWriter buffer = new BufferedWriter(writer);
            //
            for (Word var : dictionary.getAllWords()) {
                buffer.write(var.getTarget()+"\t"+var.getExplain());
                buffer.newLine();
            }
            buffer.close();
            System.out.println("Export success!");
        }catch (FileNotFoundException ex) {
            //
            System.out.println("Unable to open file '" + fileName + "'");          
        }catch (IOException ex){
            //
            System.out.println("Error writing file '" + fileName + "'");          
        }
    }
    //
    public void dictionaryLookup(){
        System.out.println("Dictionary Lookup: ");
        //
        Scanner scanner = new Scanner(System.in);
        String target = scanner.nextLine().replaceAll("\\s+","");
        //search
        int index = 0;           
        for (Word var : dictionary.getAllWords()) {
            index ++;
            //
            if(var.getTarget().equals(target)){
                System.out.println(var.getExplain()); 
                System.out.print("- Select (1)'Edit' (2)'Delete' else to 'Exit': ");
                try{
                    int c = scanner.nextInt();
                    switch(c){
                        case 1:
                            this.dictionaryEditWord(index);
                            break;
                        case 2:
                            this.dictionaryDelWord(index);
                            break;
                        default:
                            return;
                    }
                }catch (InputMismatchException iex){
                    System.out.print("Untype 'int' !!");    
                }
                //
                return;
            }
        }
        //
        System.out.println("-> Error!! Not found this word!!");
    }
    //
    public void dictionaryAddWord(){
        System.out.println("- Add a new Word");
        //
        Scanner scanner = new Scanner(System.in);
        System.out.println("target: ");
        String target = scanner.nextLine().replaceAll("\\s+","");
        System.out.println("explain: ");
        String explain = scanner.nextLine();
        //
        Word newWord = new Word(target,explain);
        dictionary.Add(newWord);
        //Add to file
        this.setFileName("dictionaries.txt");
        this.dictionaryExportToFile(this.fileName);
        //
        System.out.println("Success!");
    }
    //
    private void dictionaryEditWord(int index){
        Scanner scanner = new Scanner(System.in);
        Word word = new Word();
        //
        System.out.printf("-Edit %d by:\n",index);
        System.out.println("*target: ");
        word.setTarget(scanner.nextLine().replaceAll("\\s+",""));
        System.out.println("*explain: ");
        word.setExplain(scanner.nextLine());
        //
        System.out.print("- Select (1)'Save' (2)'Exit':");
        try{
            int c = scanner.nextInt();
            if(c==1){
                dictionary.Edit(index - 1, word);
                this.dictionaryExportToFile(this.fileName);
                System.out.println("Success!");
            }else{
                return;
            }
        }catch (InputMismatchException iex){
            return;    
        }
        //
    }
    //
    private void dictionaryDelWord(int index){
        System.out.print("- Delete this word? \n (1)'OK' (2)'Cancel':");
        try{
            Scanner scanner = new Scanner(System.in);
            int c = scanner.nextInt();
            if(c==1){
                dictionary.Delete(index - 1);
                this.dictionaryExportToFile(this.fileName);
                System.out.println("Success!");
            }else{
                return;
            }
        }catch (InputMismatchException iex){
            return;    
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
