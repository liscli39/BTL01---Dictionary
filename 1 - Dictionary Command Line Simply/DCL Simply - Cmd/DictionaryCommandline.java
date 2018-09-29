import java.util.List;

public class DictionaryCommandline{
    private Dictionary dictionary;
    public static void main(String[] args){
        DictionaryCommandline dc = new DictionaryCommandline();
        dc.dictionaryBasic();
    }

    private void showAllWords(){
        List<Word> words = dictionary.getAllWords();
        //
        int i = 1;
        System.out.println("No\t |English\t |Vietnamese");
        for (Word var : words) {
            System.out.printf("%d\t |%s\t |%s \n", i, var.getTarget(), var.getExplain());
            i++;
        }
    }

    private void dictionaryBasic(){
        dictionary = new DictionaryManagement().insertFromCommandline();
        this.showAllWords();
    }
}