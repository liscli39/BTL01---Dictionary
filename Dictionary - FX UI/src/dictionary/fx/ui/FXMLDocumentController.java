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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import project.Entity.Word;
import project.management.DictionaryManagement;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
/**
 *
 * @author Liscli
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private TextField searchBox;
    @FXML
    private ImageView exitButton;
    @FXML
    private TextArea textArea;
    @FXML
    protected ContextMenu contextMenu;
    @FXML
    private void handleButtonAction(MouseEvent event) {
        if(event.getTarget() == exitButton){
            System.exit(1); 
        }
    }
    private void search(String newValue){
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
                    setExplain(id);
                }
            });
            contextMenu.getItems().add(m);
            currentList.add(var);
        }
        contextMenu.show(searchBox,Side.BOTTOM,0 ,0);
    }
    private void setExplain(int id){
        String target = currentList.get(id).getTarget();
        String[] explains = currentList.get(id).getExplain().split(";");
        for(String var : explains){
            textArea.appendText(var);
            textArea.appendText("\n");
        }
        searchBox.setText(target);
        contextMenu.hide();
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
