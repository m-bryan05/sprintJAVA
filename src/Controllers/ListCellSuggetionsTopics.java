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

public class ListCellSuggetionsTopics extends ListCell<Topic> {
    @FXML
    private AnchorPane topicCell;
    @FXML
    private Label topicSug;
   
    @FXML
    private Label senderName;
    @FXML
    private Label role;
    @FXML
    private Label emailText;
    @FXML
    private Label message;

    private FXMLLoader mLLoader;
 
    protected void updateItem(Topic topic, boolean empty) {
        super.updateItem(topic, empty);

        if(empty || topic == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/GUI/listCellSuggetionsTopics.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            

            topicSug.setText(topic.getNom());
            emailText.setText(topic.getEmail());
            role.setText(topic.getRole());
            message.setText(topic.getMessage());
            
            
               
                
          
            setText(null);
            setGraphic(topicCell);
        }
    }

}