/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dcl.simply.netbeans;

import java.util.*;

/**
 *
 * @author Liscli
 */
public class DictionaryManagement{
    //
    //insert word(s) into dictionary from commandline
    public Dictionary insertFromCommandline(){
        Dictionary dictionary = new Dictionary(); 
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
}
