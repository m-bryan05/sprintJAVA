/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

/**
 *
 * @author ASUS
 */



import Entities.Publication;
import Entities.Topic;
import java.io.ByteArrayInputStream;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.ImageView;

public class ListCellTopics1 extends ListCell<Topic> {
    @FXML
    private AnchorPane topicCell;


    @FXML
    private Label topicName;
     @FXML
    private ImageView deleteIconView;

   

    
 

    private FXMLLoader mLLoader;
 
    protected void updateItem(Topic topic, boolean empty) {
        
        super.updateItem(topic, empty);

        if(empty || topic == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/GUI/listCellTopics.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            

            topicName.setText(topic.getNom());
          deleteIconView.setImage(new Image("file:C:\\Users\\ASUS\\Documents\\NetBeansProjects\\Pidev2\\src\\Images\\icons8-delete.png"));
          
            setText(null);
            setGraphic(topicCell);
        }
    }

}