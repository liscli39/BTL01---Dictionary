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
        return dictionary.add(newWord);
    }
}