/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary.fx.ui;

import java.net.URL;
import java.util.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import project.Entity.Word;
import project.management.DictionaryManagement;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javax.swing.JFrame;

/**
 *
 * @author Liscli
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private Label auther,idLabel;
    @FXML
    private AnchorPane definition,editPane,about,anchorPane;
    @FXML
    private TextField searchBox,textField_;
    @FXML
    private ImageView exitButton,addButton,editButton,deleteButton,speakButton,
            scrollButton,cancelButton,saveButton,minimize,minimize1;
    @FXML
    private TextArea textArea,textArea_;
    @FXML
    protected ContextMenu contextMenu;
    @FXML
    private void handleButtonAction(MouseEvent event) {
        if(event.getTarget() == exitButton){
            System.exit(1); 
        }else if(event.getTarget() == addButton){
                about.setVisible(false);
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
        }else if(event.getTarget() == speakButton){
            speak();
        }else if(event.getTarget() == cancelButton){
            auther.toFront();
            editPane.toBack();
            textField_.setText("");
            textArea_.setText("");
            definition.setVisible(false);
            about.setVisible(true);
            searchBox.setEditable(true);
        }else if(event.getTarget() == saveButton){
            Word w;
            if(idLabel.getText().equals("-1")){
                addNewWord();
            }else{
//                System.out.println("edit");
                editWord();
            }
            auther.toFront();
            editPane.toBack();
            textField_.setText("");
            textArea_.setText("");
            searchBox.setEditable(true);
        }else if(event.getTarget() == scrollButton){
            searchBox.setText("");
            exitButton.requestFocus();
            definition.setVisible(false);
            about.setVisible(true);
        }else if(event.getTarget() == minimize || event.getTarget() == minimize1){
            minimizeWindow();
        }else if(event.getTarget() == searchBox){
            searchBox.selectAll();
        }
        
    }
    @FXML
    private void deleteButtonAction(MouseEvent event){
        deleteWord();
    }
//    @FXML
//    private void aboutButtonAction(MouseEvent event){
//        javax.swing.JFrame about = new javax.swing.JFrame("Dictionary");
//        about.setSize(100, 100);
//        about.setResizable(false);
//        about.show();
//        
//    }
//    
    private void search(String newValue){
        if(newValue.equals("")) return;
        currentList.clear();
        management.getListWordsWithQuery(newValue);
        List<Word> words = management.getDictionary().getAllWords();
        
        contextMenu.hide();
        contextMenu.getItems().clear();
        Integer ind = 0; 
        
        for (Word var : words){
            MenuItem m = new MenuItem(var.getTarget());
            m.setId(ind.toString());
            ind++;
            m.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int id = Integer.parseInt(m.getId());
                    definition.setVisible(true);
                    about.setVisible(false);
                    setExplain(id);
                }
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
        if(!textField_.getText().equals("")){
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
        if (!(idLabel.getText().equals("-1") || idLabel.getText().equals("id"))){
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
        TextToSpeech tts = new TextToSpeech();      
        tts.speak(searchBox.getText(), 1.0f, false, false);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        management  = new DictionaryManagement();    
        currentList = new LinkedList<>();
        searchBox.textProperty().addListener((observable, oldValue, newValue) -> 
        {
            search(newValue);
        });
    }                                                        
    private DictionaryManagement management;    
    private List<Word> currentList;
}