public class Word{
    // init
    private String word_target;
    private String word_explain;
    Word(String target,String explain){
        word_target = target;
        word_explain = explain;
    }
    // getter,setter
    public String getTarget(){
        return word_target;
    }
    public String getExplain(){
        return word_explain;
    }
    public void setTarget(String target){
        word_target = target;
    }
    public void setExplain(String explain){
        word_explain = explain;
    }
}