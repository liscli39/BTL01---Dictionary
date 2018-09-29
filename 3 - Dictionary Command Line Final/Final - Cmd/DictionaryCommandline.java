import java.util.*;

public class DictionaryCommandline{
    DictionaryCommandline(){
        management = new DictionaryManagement();
    }
    public static void main(String[] args){
        DictionaryCommandline dc = new DictionaryCommandline();
        dc.dictionaryAdvanced();
    }
    //
    private void showAllWords(){
        List<Word> words =  management.getDictionary().getAllWords();
        //
        int i = 1;
        System.out.println("--------------------------------------");
        System.out.printf("%3s %-20s %s\n","No","| English","| Vietnamese");
        System.out.println("--------------------------------------");
        //
        for (Word var : words) {
            System.out.printf("%3d %-20s %s\n", i, "| "+var.getTarget(),"| " + var.getExplain());
            i++;
        }
        //
        System.out.println("--------------------------------------");
    }
    private void dictionaryAdvanced(){
        management.insertFromFile();
        this.showAllWords();
        this.dictionarySearcher();
        management.dictionaryLookup();
    } 
    private void dictionaryBasic(){
        management.insertFromCommandline();
        this.showAllWords();
    }
    private void dictionarySearcher(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("- Search for: ");
        String searchWord = scanner.nextLine().replaceAll("\\s+","");
        //
        System.out.println("- Result: ");
        List<Word> words = management.getDictionary().getAllWords();
        boolean had = false;
        //
        for (Word var : words) {
            if(this.compare(var.getTarget(),searchWord)){
                System.out.println(var.getTarget());
                had = true;
            }
        }
        //
        if(!had) System.out.println("-> Not found!!");
        //scanner.close();
    }
    private boolean compare(String s1,String s2){
        for(int i=0;i<Math.min( s1.length(), s2.length());i++){
            if( (s1.charAt(i)!=s2.charAt(i)) ) return false;
        }
        return true;
    }
    private DictionaryManagement management;
}