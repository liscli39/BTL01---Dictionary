/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dcl.simply.netbeans;

/**
 *
 * @author Liscli
 */
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
