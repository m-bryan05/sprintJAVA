/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import Entities.User;
import Services.UserService;
import Utils.Validations;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author MAB
 */
public class ProfileEditFormController implements Initializable {

    @FXML
    private ImageView userImage;
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private CheckBox admin;
    @FXML
    private TextField name;
    @FXML
    private TextField secondName;
    @FXML
    private DatePicker birthdate;
    @FXML
    private CheckBox enabled;
    @FXML
    private CheckBox verified;
    @FXML
    private TextField coins;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private Button cancel;

    public void setUserImage(String url) {
        this.userImage.setImage(new Image(url));
    }

    public void setUsername(String username) {
        this.username.setText(username);
    }

    public void setEmail(String email) {
        this.email.setText(email);
    }

    public void setAdmin(boolean admin) {
        this.admin.setSelected(admin);
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public void setSecondName(String secondName) {
        this.secondName.setText(secondName);
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate.setValue(birthdate.toLocalDate());
    }

    public void setEnabled(boolean enabled) {
        this.enabled.setSelected(enabled);
    }

    public void setVerified(boolean verified) {
        this.verified.setSelected(verified);
    }

    public void setCoins(int coins) {
        this.coins.setText(Integer.toString(coins));
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleClicks(ActionEvent event) {
        if (event.getSource() == update) {
            if (!Validations.isNumberValid(coins.getText())) {
                System.out.println("not a number");
            } else {
                User user = new User(enabled.isSelected() ? 1 : 0, Integer.parseInt(coins.getText()), verified.isSelected() ? 1 : 0, username.getText(), admin.isSelected() ? "[\"ROLE_ADMIN\"]" : "[\"ROLE_USER\"]", email.getText(), name.getText(), secondName.getText(), birthdate.getValue()!=null?Date.valueOf(birthdate.getValue()):null);
                try {
                    new UserService().updateNoPass(user);
                } catch (SQLException ex) {
                    Logger.getLogger(ProfileEditFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (event.getSource() == delete) {
            try {
                new UserService().deleteByUsername(username.getText());
                FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("../Views/Home.fxml"));
                Parent homeRoot = homeLoader.load();
                HomeController homeCtrl = homeLoader.getController();
                homeCtrl.changePage("users");
                username.getScene().setRoot(homeRoot);
            } catch (Exception ex) {
                Logger.getLogger(ProfileEditFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("../Views/Home.fxml"));
                Parent homeRoot = homeLoader.load();
                HomeController homeCtrl = homeLoader.getController();
                homeCtrl.changePage("users");
                username.getScene().setRoot(homeRoot);
            } catch (Exception ex) {
                Logger.getLogger(ProfileEditFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
