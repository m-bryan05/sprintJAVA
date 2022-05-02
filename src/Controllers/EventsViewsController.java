/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Events;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import service.ServiceEvents;

/**
 * FXML Controller class
 *
 * @author Endezoumou Bryan
 */
public class EventsViewsController implements Initializable {

    @FXML
    private Button event_add;

    @FXML
    private TextField txt_categorie;

    @FXML
    private DatePicker txt_datedebut;

    @FXML
    private DatePicker txt_datefin;

    @FXML
    private TextArea txt_description;

    @FXML
    private TextField txt_saisie;

    @FXML
    private TextField txt_titre;

    @FXML
    void addEvents_btn(ActionEvent event) {
        if(!txt_titre.getText().equals("")){
                    if(!txt_categorie.getText().equals("")){
                        ServiceEvents serviceevents = new ServiceEvents();
                        String titre = txt_titre.getText();
                        String categorie = txt_categorie.getText();
                        Date datedebut = Date.valueOf(txt_datedebut.getValue());
                        Date datefin = Date.valueOf(txt_datefin.getValue());
                        String description = txt_description.getText();
                        Events e = new Events(titre,categorie,datedebut,datefin,description);
                        serviceevents.ajouterEvents(e);
                    }
                    else{
                        //message.error_message("Erreur", "Erreur du champ", "Veuillez entrer le prenom de l'admin SVP !");
                        System.out.println("Erreur!!! Erreur du champ!! Veuillez entrer le prenom de l'admin SVP !");
                    }
                }else{
                    //message.error_message("Erreur", "Erreur du champ", "Veuillez entrer le nom du restaurant SVP !");
                    System.out.println("Erreur!!! Erreur du champ!! Veuillez entrer le nom de l'admin SVP !");
                }
            
    }

    @FXML
    void saisie_categorie(MouseEvent event) {

    }

    @FXML
    void saisie_debut(MouseEvent event) {

    }

    @FXML
    void saisie_description(MouseEvent event) {

    }

    @FXML
    void saisie_fin(MouseEvent event) {

    }

    @FXML
    void saisie_titre(MouseEvent event) {

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
}
