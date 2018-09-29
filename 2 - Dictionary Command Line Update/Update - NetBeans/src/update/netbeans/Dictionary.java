/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package update.netbeans;

import java.util.*;
/**
 *
 * @author Liscli
 */
public class Dictionary {
    private final List<Word> dictionary;

    public Dictionary(){
        dictionary = new LinkedList<Word>();
    }
    //Get all the words in the dictionary
    public List<Word> getAllWords(){
        return dictionary;
    }
    //Add a new word into dictionary
    public boolean Add(Word newWord){
        return dictionary.add(newWord);
    }
}
