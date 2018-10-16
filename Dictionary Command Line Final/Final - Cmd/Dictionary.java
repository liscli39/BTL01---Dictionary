import java.util.*;

public class Dictionary{
    private List<Word> dictionary;

    public Dictionary(){
        dictionary = new LinkedList<Word>();
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