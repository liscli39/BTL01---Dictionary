/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary.fx.ui;

import java.net.URL;
import java.util.*;
import java.awt.datatransfer.*;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import javax.swing.JOptionPane;

import project.Entity.Word;
import project.management.DictionaryManagement;

import tts.TextToSpeech;
/**
 *
 * @author Liscli
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private ProgressIndicator progress;
    @FXML
    private Label auther,idLabel;
    @FXML
    private AnchorPane definition,editPane,anchorPane;
    @FXML
    private TextField searchBox,textField_;
    @FXML
    private ImageView exitButton,addButton,editButton,
           cancelButton,saveButton,minimize,minimize1;
    @FXML
    private TextArea textArea,textArea_;
    @FXML
    private ContextMenu contextMenu;
    @FXML
    private void handleButtonAction(MouseEvent event) {
        if(event.getTarget() == exitButton){
            System.exit(1); 
        }else if(event.getTarget() == addButton){
                definition.setVisible(true);
                searchBox.setText("");
                searchBox.setEditable(false);
                textField_.setText("");
                textArea_.setText("");
                idLabel.setText("-1");
                editPane.toFront();
                auther.toFront();
                contextMenu.hide();
                textField_.requestFocus();
        }else if(event.getTarget() == editButton){
            if(!searchBox.getText().equals("") && definition.isVisible()){
                editPane.toFront();
                auther.toFront();
                //
                textField_.setText(searchBox.getText());
                textArea_.setText(textArea.getText());
                textField_.requestFocus();
            }
        }else if(event.getTarget() == cancelButton){
            auther.toFront();
            editPane.toBack();
            textField_.setText("");
            textArea_.setText("");
            definition.setVisible(false);
            searchBox.setEditable(true);
        }else if(event.getTarget() == saveButton){
            Word w;
            if(idLabel.getText().equals("-1")){
                addNewWord();
            }else{
                editWord();
            }
            auther.toFront();
            editPane.toBack();
            textField_.setText("");
            textArea_.setText("");
            searchBox.setEditable(true);
        }else if(event.getTarget() == minimize || event.getTarget() == minimize1){
            minimizeWindow();
        }
        
    }
    @FXML
    private void deleteButtonAction(MouseEvent event){
        deleteWord();
    }
    @FXML
    private void aboutButtonAction(MouseEvent event){
        String aboutString = "Dictionary\n"
        + "Auther: Liscli and Ngoc Huyen\n"
        + "GitHub: https://github.com/liscli39\n"
        + "Icon: https://icons8.com/ \n"
        + "Text to speech : by GOXR3PLUS (with MarryTTS library) \n"
        + "https://github.com/goxr3plus\n"
        + "E.V Database: lingoes.net \n"
        + "DBMS: sqlite";        
        JOptionPane.showMessageDialog(null, aboutString,"About",1);
    }
    @FXML
    private void speakButtonAction(MouseEvent event){
        speak();
    }
    @FXML
    private void searchBoxMouseClickedAction(MouseEvent event){
        idLabel.setText("-1");
        textArea.clear();
        searchBox.selectAll();
        progress.setVisible(false);
    }
    @FXML
    private void copyButtonAction(MouseEvent event){
        StringSelection stringSelection = new StringSelection(textArea.getText());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }
    @FXML
    private void updownButtonAction(MouseEvent event){
        if(definition.isVisible()){
            exitButton.requestFocus();
            definition.setVisible(false);
        }else{
            exitButton.requestFocus();
            definition.setVisible(true);
        }
    }
    private void search(String newValue){
        if(newValue.equals("")) {
            progress.setVisible(false);
            return;
        }
        progress.setVisible(false);
        currentList.clear();
        management.getListWordsWithQuery(newValue);
        
        contextMenu.hide();
        contextMenu.getItems().clear();
        Integer ind = 0; 
        if(management.getDictionary().getAllWords().isEmpty()) {    
            progress.setVisible(true);
            return;
        }
        for (Word var : management.getDictionary().getAllWords()){
            MenuItem m = new MenuItem(var.getTarget());
            m.setId(ind.toString());
            ind++;
            m.setOnAction((ActionEvent event) -> {
                definition.setVisible(true);
//                about.setVisible(false);
                setExplain(new Integer(m.getId()));
            });
            contextMenu.getItems().add(m);
            currentList.add(var);
        }
        contextMenu.show(searchBox,Side.BOTTOM,0 ,0);
    }
    private void setExplain(int id){
        textArea.clear();
        String target = currentList.get(id).getTarget();
        String[] explains = currentList.get(id).getExplain().split(";");
        Integer idx = currentList.get(id).getId();
        for(String var : explains){
            textArea.appendText(var);
            textArea.appendText("\n");
        }
        searchBox.setText(target);
        idLabel.setText(idx.toString());
        contextMenu.hide();
        textArea.requestFocus();
    }
    private void addNewWord(){
        if(!textField_.getText().equals("")){
            searchBox.setText(textField_.getText());
            textArea.setText(textArea_.getText());
            String explain = textArea_.getText().replaceAll("\n", ";");
            String target = textField_.getText();
            Word w = new Word(0,target,explain);
            management.Add(w);
        }
    }
    private void editWord(){
        if(!textField_.getText().equals("")&& !idLabel.getText().equals("-1") ){
            textArea.setText(textArea_.getText());
            //
            int id = Integer.parseInt(idLabel.getText());
            String explain = textArea_.getText().replaceAll("\n", ";");
            String target = textField_.getText();
            Word w = new Word(id,target,explain);
            management.Edit(w); 
        }
    }
    private void deleteWord(){
        if (!(idLabel.getText().equals("-1") || idLabel.getText().equals("id"))
                && !searchBox.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Word");
            alert.setHeaderText("Are you sure you want to delete this word?");
            alert.setContentText(searchBox.getText());
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == null) {
                
            } else if (option.get() == ButtonType.OK) {
                int id = Integer.parseInt(idLabel.getText());
                management.Delete(id);
                searchBox.setText("");
                textArea.setText("");
                idLabel.setText("-1");
                textArea_.requestFocus();
            } else if (option.get() == ButtonType.CANCEL){
                
            }
        }
    }
    private void minimizeWindow(){
        Stage stage = (Stage)anchorPane.getScene().getWindow();
        stage.setIconified(true);
    }
    private void speak(){
        tts.speak(searchBox.getText(), 1.0f, true, false);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        management  = new DictionaryManagement();    
        currentList = new LinkedList<>();
        searchBox.textProperty().addListener((observable, oldValue, newValue) -> 
        {
            search(newValue);
        });
        tts = new TextToSpeech();      
    }                                                        
    private DictionaryManagement management;    
    private List<Word> currentList;
    private TextToSpeech tts;
}