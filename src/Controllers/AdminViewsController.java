/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Admins;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.mail.Message;
import service.ServiceAdmins;
import service.ServiceAdmins;

/**
 * FXML Controller class
 *
 * @author Endezoumou Bryan
 */
public class AdminViewsController implements Initializable {
    
    
    @FXML
    private Button admin_add;

    @FXML
    private Button admin_delete;

    @FXML
    private Button admin_edit;

    @FXML
    private Button admin_searchadmin;

    @FXML
    private Button admin_update;

    @FXML
    private ListView<Admins> mylistadmins;

    @FXML
    private TextField txt_adresse;

    @FXML
    private TextField txt_mail;

    @FXML
    private TextField txt_nom;

    @FXML
    private TextField txt_prenom;

    @FXML
    private TextField txt_searchadmin;

    @FXML
    private TextField txt_tel;
    
    @FXML
    void ActualiserAdmins(ActionEvent event) {
           showAdmins();
    }
    
    @FXML
    void update_admins(ActionEvent event) {
        //Message message = new Message();
        
                if(!txt_nom.getText().equals("")){
                    if(!txt_prenom.getText().equals("")){

                            ServiceAdmins servicesadmins = new ServiceAdmins();
                            String nom_admins = txt_nom.getText();
                            String prenom_admins = txt_prenom.getText();
                            String mail_admins = txt_mail.getText();
                            int tel_admins = Integer.parseInt(txt_tel.getText());
                            String adresse_admins = txt_adresse.getText();
                            servicesadmins.modifier2(nom_admins,prenom_admins,mail_admins,tel_admins,adresse_admins);
                            
                            //ob_list_admin.clear();
                            
                            
                            //message.valide_message("Succes", "Modifier reussite", "L'administrateur a été modifier avec succès !");
                            System.out.println("Succes!!!Modifier reussite, L'administrateur a été modifier avec succès !");
                            
                            txt_nom.setText("");
                            txt_prenom.setText("");
                            txt_mail.setText("");
                            txt_tel.setText("");
                            txt_adresse.setText("");

                    }else{
                        //message.error_message("Erreur", "Erreur du champ", "Veuillez entrer le prenom de l'admin SVP !");
                        System.out.println("Erreur!!! Erreur du champ!! Veuillez entrer le prenom de l'admin SVP !");
                    }
                }else{
                    //message.error_message("Erreur", "Erreur du champ", "Veuillez entrer le nom du restaurant SVP !");
                    System.out.println("Erreur!!! Erreur du champ!! Veuillez entrer le nom de l'admin SVP !");
                }
    }
    
    String food[] = {"Bryan","Fox","Red"};
    String currentFood;
    
    private ObservableList<String> ob_list_admin;
    
     @FXML
    void addAdmins(ActionEvent event) {
        if(!txt_nom.getText().equals("")){
                    if(!txt_prenom.getText().equals("")){
                        ServiceAdmins serviceadmins = new ServiceAdmins();
                        String nom_admins = txt_nom.getText();
                        String prenom_admins = txt_prenom.getText();
                        String mail_admins = txt_mail.getText();
                        int tel_admins = Integer.parseInt(txt_tel.getText());
                        String adresse_admins = txt_adresse.getText();
                        Admins a = new Admins(nom_admins,prenom_admins,mail_admins,tel_admins,adresse_admins);
                        serviceadmins.ajouterAdmins(a);
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
    void search_admins(ActionEvent event) {
        /*Message message = new Message();
        
        if(!txt_searchadmin.getText().equals("")){
            String nom = txt_searchadmin.getText();
            ServiceAdmins Admins = new ServiceAdmins();
            ArrayList<Admins> list_recherche = new ArrayList<>();
            list_recherche = (ArrayList<Admins>) Admins.recherche_nom(nom);

            ob_list_admin = FXCollections.observableArrayList(list_recherche);
            
            list_admins.setItems(ob_list_restaurant);


        }else{
            message.error_message("Erreur", "Erreur du champ", "Veuillez entrer un resultat à rechercher !");
        }*/
    }


    // methode d'affichage des admins
    /*@FXML
    public void show_admins(){
        ServiceAdmins admins = new ServiceAdmins();
        ArrayList<Admins> list_admin = new ArrayList<>();
        list_admin = (ArrayList<Admins>) admins.afficherAdmins();
        
        ob_list_admin = FXCollections.observableArrayList(list_admin);
        
        list_admins.setItems(ob_list_admin);
        
        
    }*/
        public void showAdmins() {
        ObservableList<Admins> admins = null;
        ServiceAdmins serviceadmins = new ServiceAdmins();
        try {
            admins = serviceadmins.getAll();
            
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        mylistadmins.setItems(admins);
        mylistadmins.setCellFactory(studentListView -> new ListCellCommande());
    }
        
        /*public void addAdmins() {
            String sql = "insert into administrateurs (nom,prenom,mail,tel,adresse) values(?,?,?,?,?)";
            try {
                pst = 
            }
        }*/
    
       
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        /*col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<>("surname"));
        col_adresse.setCellValueFactory(new PropertyValueFactory<>("mail"));
        col_mail.setCellValueFactory(new PropertyValueFactory<>("tel"));
        col_tel.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        
        show_admins();*/
        showAdmins();
      // mylistadmins.getItems().addAll(food);

    }    
    
}
