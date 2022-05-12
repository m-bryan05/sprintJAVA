/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import GymConnect.GymConnect;
import Services.UserService;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author MAB
 */
public class ProfileUpdateController implements Initializable {

    @FXML
    private Button update;
    @FXML
    private Button cancel;
    @FXML
    private TextField name;
    @FXML
    private TextField secondName;
    @FXML
    private TextField email;
    @FXML
    private DatePicker birthdate;
    @FXML
    private Label errorMsg;

    /**
     * Initializes the controller class.
     */
    public void setInfos() {
        this.name.setText(GymConnect.loggedUser.getName());
        this.secondName.setText(GymConnect.loggedUser.getSecondName());
        this.email.setText(GymConnect.loggedUser.getEmail());
        if (GymConnect.loggedUser.getBirthDate() != null) {
            this.birthdate.setValue(GymConnect.loggedUser.getBirthDate().toLocalDate());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setInfos();
        errorMsg.setText("");
    }

    @FXML
    private void handleClicks(ActionEvent event) {
        if (event.getSource() == update) {
            GymConnect.loggedUser.setName(name.getText());
            GymConnect.loggedUser.setSecondName(secondName.getText());
            GymConnect.loggedUser.setEmail(email.getText());
            if(birthdate.getValue() != null)
                GymConnect.loggedUser.setBirthDate(Date.valueOf(birthdate.getValue()));
            UserService us = new UserService();
            try {
                us.update(GymConnect.loggedUser);
            } catch (SQLException ex) {
                Logger.getLogger(ProfileUpdateController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("../Views/HomeFront.fxml"));
            Parent homeNode = homeLoader.load();
            ((HomeFrontController) homeLoader.getController()).changePage("profile");
            cancel.getScene().setRoot(homeNode);
        } catch (Exception ex) {
            Logger.getLogger(ProfileEditFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
