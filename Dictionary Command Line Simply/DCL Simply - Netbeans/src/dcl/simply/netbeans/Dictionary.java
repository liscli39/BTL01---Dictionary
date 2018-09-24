/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dcl.simply.netbeans;

import java.util.*;
import java.util.List;
/**
 *
 * @author Liscli
 */
public class Dictionary{
    private final Map<Integer,Word> dictionary;
    private int numWords;

    public Dictionary(){
        dictionary = new HashMap<Integer,Word>();
        numWords = 0;
    }
    //
    //
    //Get all the words in the dictionary
    public List<Word> getAllWords(){
        java.util.List<Word> words = new LinkedList<Word>();
        Set keys = dictionary.keySet();
        for(Object k : keys){
            words.add(dictionary.get(k));
        }
        return words;
    }

    //Add a new word into dictionary
    public void Add(Word newWord){
        dictionary.put(numWords,newWord);
        numWords ++;
    }
    //
    //
}
