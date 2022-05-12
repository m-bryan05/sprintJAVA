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



import Entities.Commentaire;

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

public class ListCellCommentsController extends ListCell<Commentaire> {
    @FXML
    private AnchorPane ComCell;
    @FXML
    private Label nom;

    @FXML
    private Label email;

    @FXML
    private Label comment;
     @FXML
    private Label idC;

    private FXMLLoader mLLoader;
 
    protected void updateItem(Commentaire commentaire, boolean empty) {
        super.updateItem(commentaire, empty);

        if(empty || commentaire == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/GUI/listeCellComments.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            
            nom.setText(commentaire.getNom().toUpperCase());
        
            comment.setText(commentaire.getCommentaire()) ;
         
      
            setText(null);
            setGraphic(ComCell);
        }
    }

}