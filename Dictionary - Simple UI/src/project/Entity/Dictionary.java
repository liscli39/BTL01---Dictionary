/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.Entity;

import java.util.*;
/**
 *
 * @author Liscli
 */
public class Dictionary {
    private final List<Word> dictionary;

    public Dictionary(){
        dictionary = new LinkedList<>();
    }
    public void setListWords(List<Word> words){
        dictionary.addAll(words);
    }
    //Get all the words in the dictionary
    public List<Word> getAllWords(){
        return dictionary;
    }
    //Add a new word into dictionary
    public boolean Add(Word newWord){
        try{
            return dictionary.add(newWord);
        }catch (NullPointerException ex){
            return false;
        }
    }
    // Get one word with index
    public Word get(int index){
        try{
        return dictionary.get(index);
        }catch (IndexOutOfBoundsException ex){
            return null;
        }
    }
    //
    public Boolean Edit(int index, Word word){
        try{
            dictionary.set(index, word);
            return true;
        }catch (IndexOutOfBoundsException ex){
            return false;
        }
    }
    public Boolean Delete(int index){
        try{
            dictionary.remove(index);
            return true;
        }catch (NoSuchElementException ex){
            return false;
        }
    }
}
