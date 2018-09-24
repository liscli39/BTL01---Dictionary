import java.util.*;

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