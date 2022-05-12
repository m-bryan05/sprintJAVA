/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import Entities.User;
import pidev.Main;
import Services.UserService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import static pidev.Main.main;

/**
 * FXML Controller class
 *
 * @author MB
 */
public class HomeController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button btnOverview;
   
    @FXML
    private Button btnUsers;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private ImageView userImage;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label titleLabel;
    @FXML
    private Pane mainContent;
    @FXML
    private Button addBtn;
     private Stage stage;
    private Scene scene;
    private Parent root;
    private FXMLLoader fxmlLoader;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usernameLabel.setText(Main.loggedUser.getUsername());
        userImage.setImage(new Image("https://avatars.dicebear.com/api/bottts/" + Main.loggedUser.getUsername() + ".png"));
    }
 
    public void changePage(String title, Parent node) {
        titleLabel.setText(title);
        addBtn.setVisible(false);
        mainContent.getChildren().removeAll(mainContent.getChildren());
        mainContent.getChildren().add(node);
    }

    public void changePage(String state) {
        addBtn.setVisible(false);
        if (state.equals("users")) {
            titleLabel.setText("Users");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/UsersFormContent.fxml"));
                Parent root = loader.load();
                mainContent.getChildren().add(root);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } 
    }

    @FXML
    private void handleClicks(ActionEvent event) {
        mainContent.getChildren().removeAll(mainContent.getChildren());
        addBtn.setVisible(false);
        if (event.getSource() == btnSignout) {
            Main.loggedUser = new User();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/LoginForm.fxml"));
                Parent root = loader.load();
                btnSignout.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(LoginFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event.getSource() == btnUsers) {
            titleLabel.setText("Users");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/UsersFormContent.fxml"));
                Parent root = loader.load();
                mainContent.getChildren().add(root);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        }  else {
            System.out.println(event.getSource());
        }
    }

    @FXML
    private void addClick(ActionEvent event) {
    }
  
   
    

}
