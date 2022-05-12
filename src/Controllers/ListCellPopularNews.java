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

public class ListCellPopularNews extends ListCell<Publication> {
    @FXML
    private AnchorPane PubCell;


    @FXML
    private Label title;

    @FXML
    private Label desc;

    @FXML
    private Label type;
     @FXML
    private Label test;
    
    @FXML
    private ImageView imageView ;
    byte byteImg[] ;

    
 

    private FXMLLoader mLLoader;
 
    protected void updateItem(Publication publication, boolean empty) {
        super.updateItem(publication, empty);

        if(empty || publication == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/GUI/listeCellPopularNews.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            

            title.setText(publication.getTitle());
            desc.setText(publication.getDescription());
            type.setText(publication.getType());
            
            try {
                byteImg=publication.getImage().getBytes(1, (int) publication.getImage().length());
                Image img = new Image(new ByteArrayInputStream(byteImg),imageView.getFitHeight(),imageView.getFitHeight(),true,true) ;
                imageView.setImage(img);
                
                //imageView.setImage(new Image(publication.getImage())); 
            } catch (SQLException ex) {
                Logger.getLogger(ListCellPopularNews.class.getName()).log(Level.SEVERE, null, ex);
            }
            
 
            
      
            setText(null);
            setGraphic(PubCell);
        }
    }

}