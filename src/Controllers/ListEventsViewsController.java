/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Endezoumou Bryan
 */
public class ListEventsViewsController implements Initializable {

    @FXML
    private VBox cardEvent;

    @FXML
    private ImageView eventimg;

    @FXML
    private GridPane grid;

    @FXML
    private Label namecategorieprincipal;

    @FXML
    private Label nameeventprincipal;

    @FXML
    private ScrollPane scroll;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
