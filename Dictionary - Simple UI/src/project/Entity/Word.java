/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.Entity;

/**
 *
 * @author Liscli
 */
public class Word {
    // init
    private int word_id;
    private String word_target;
    private String word_explain;
    public Word(){
        word_id = 0;
        word_target = "";
        word_explain = "";
    }
    public Word(int id,String target,String explain){
        word_target = target;
        word_explain = explain;
    }
    // getter,setter
    public int getId(){
        return word_id;
    }
    public String getTarget(){
        return word_target;
    }
    public String getExplain(){
        return word_explain;
    }
    public void setId(int id){
        word_id = id;
    }
    public void setTarget(String target){
        word_target = target;
    }
    public void setExplain(String explain){
        word_explain = explain;
    }
}
