/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Events;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import service.ServiceEvents;
import utiles.MaConnexion;

/**
 * FXML Controller class
 *
 * @author Endezoumou Bryan
 */
public class EventsViewsController implements Initializable {

    @FXML
    private Rectangle drag_image;

    @FXML
    private Button event_add;

    @FXML
    private Button import_btn;

    @FXML
    private ComboBox<?> txt_categorie;

    @FXML
    private DatePicker txt_datedebut;

    @FXML
    private DatePicker txt_datefin;

    @FXML
    private TextArea txt_description;

    @FXML
    private Label txt_image;

    @FXML
    private TextField txt_saisie;

    @FXML
    private TextField txt_titre;

    private String imPath;
    
    @FXML
    void addEvents_btn(ActionEvent event) throws SQLException {
        if(!txt_titre.getText().equals("")){
                   // if(!txt_categorie.getText().equals("")){
                        String request = "insert into evenements (titre,categorie,datedebut,datefin,description,image) values(?,?,?,?,?,?)";
                        ServiceEvents serviceevents = new ServiceEvents();
                        String titre = txt_titre.getText();
                        String categorie = "";
                        //String categorie = txt_categorie.getText();
                        Date datedebut = Date.valueOf(txt_datedebut.getValue());
                        Date datefin = Date.valueOf(txt_datefin.getValue());
                        String description = txt_description.getText();
                        //Blob images = Blob.valueOf(drag_image.getValue());
                        Events e = new Events(titre, categorie, datedebut, datefin, description);
                        Connection cnx = MaConnexion.getInstance().getCnx();
                            PreparedStatement ps = cnx.prepareStatement(request);
                            ps.setString(1, e.getTitre());
                            ps.setString(2, e.getCategorie());
                            ps.setString(3, String.valueOf(e.getDateDebut()));
                            ps.setString(4, String.valueOf(e.getDateFin()));
                            ps.setString(5, e.getDescription());
                            if(imPath != null){
                            //try {
                                InputStream is;
                            try {
                                is = new FileInputStream(new File(imPath));
                                ps.setBlob(6, is);
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(EventsViewsController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            ps.executeUpdate();
                            System.out.println("Ajout");
                            
                            }
        }
        else{
            //message.error_message("Erreur", "Erreur du champ", "Veuillez entrer le prenom de l'admin SVP !");
            System.out.println("Erreur!!! Erreur du champ!! Veuillez entrer le prenom de l'admin SVP !");
            }
                //}else{
                    //message.error_message("Erreur", "Erreur du champ", "Veuillez entrer le nom du restaurant SVP !");
                    //System.out.println("Erreur!!! Erreur du champ!! Veuillez entrer le nom de l'admin SVP !");
                //}
            
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
    
    @FXML
    void import_img(ActionEvent event) throws IOException{
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterjpg = new FileChooser.ExtensionFilter("jpg files (*.png)", "*.png");
        FileChooser.ExtensionFilter extFilterpng = new FileChooser.ExtensionFilter("png files (*.jpg)", "*.jpg");
        fileChooser.getExtensionFilters().addAll(extFilterjpg, extFilterpng);

        File file = fileChooser.showOpenDialog(null);
        
        if (file != null) {
            if (file.length() < 6000000) {
                System.out.print("Condition ok");
                System.out.println(file.length());
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                drag_image.setFill(new ImagePattern(image));
                imPath = file.getAbsolutePath();
            } else {
            }
            }
    }
}
