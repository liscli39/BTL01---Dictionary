/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package update.netbeans;

import java.util.*;
import java.io.*;
/**
 *
 * @author Liscli
 */
public class DictionaryManagement {
    //insert word(s) into dictionary from COMMANDLINE
    public Dictionary insertFromCommandline(){
        dictionary = new Dictionary(); 
        //
        Scanner scanner = new Scanner(System.in); 
        int numWors = scanner.nextInt();
        scanner.nextLine(); 
        //
        for(int i=0;i<numWors;i++){
            Word newWord = new Word(scanner.nextLine(),scanner.nextLine());
            dictionary.Add(newWord);
        }
        //
        return dictionary;
    }
    //insert word(s) into dictionary from FILE
    public Dictionary insertFromFile(){
        dictionary = new Dictionary(); 
        //
        File directory = new File("");
        String fileName = directory.getAbsolutePath() + "\\dictionaries.txt";
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
        }catch (FileNotFoundException ex) {
            //
            System.out.println("Unable to open file '" + fileName + "'");          
        }catch (IOException ex){
            //
            System.out.println("Error reading file '" + fileName + "'");          
        }
        //
        return dictionary;
    }
    //
    public void dictionaryLookup(){
        System.out.println("Dictionary Lookup: ");
        //
        Scanner scanner = new Scanner(System.in); 
        String target = scanner.nextLine().replaceAll("\\s+","");
        //
        List<Word> words = dictionary.getAllWords();
            
        for (Word var : words) {
            if(var.getTarget().equals(target)){
                System.out.println(var.getExplain());
                return;
            }
        }
        System.out.println("Error!! Not found this word!!");
        scanner.close();
    }

    private Dictionary dictionary;
}
