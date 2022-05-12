/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Publication;
import Services.PublicationServices;
import Services.TopicServices;
import Utils.MaConnexion;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AjouterPublicationController implements Initializable {
    Connection cnx ;
    public PreparedStatement st ;
    public ResultSet result ;
    TopicServices tps = new TopicServices();   
      private String path;
        static Stage stage ;
         Integer idCat; 
    

    @FXML
    private TextField title;
    @FXML
    private TextField description;
    @FXML
    private ImageView imageView;
    File f;
    String erreur ;
    
    
    @FXML
    private ComboBox<String> typePub;
    ObservableList<String> listTypes = FXCollections.observableArrayList(tps.getNomTopics());
    
    @FXML
    private Button lab_url;
    private FileInputStream fs ;
    @FXML
    private ImageView logoView;
    @FXML
    private ImageView descCheckMark;
    @FXML
    private ImageView titleCheckMark;
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          cnx = MaConnexion.getInstance().getCnx();
        // TODO
        PublicationServices ps = new PublicationServices();
            title.setDisable(false);
            description.setDisable(false);
            
            typePub.setPromptText("Please select a topic");
            typePub.setItems(listTypes);
             typePub.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
             idCat = tps.retreiveIdCp(newValue);
        });
          /*  imageView.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasFiles()) {
                    event.acceptTransferModes(TransferMode.COPY);
                } else {
                    event.consume();
                }
            }
        });

        // Dropping over surface
     /*  imageView.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {
                    success = true;
                    path = null;
                    for (File file : db.getFiles()) {
                        path = file.getName();
                        selectedFile = new File(file.getAbsolutePath());
                        System.out.println("Drag and drop file done and path=" + file.getAbsolutePath());
                        imageView.setImage(new Image("file:" + file.getAbsolutePath()));
                         imageView.setFitHeight(500);
                        imageView.setFitWidth(600);
                        image.setText(path);
                    }
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });

    */    imageView.setImage(new Image("file:C:\\Users\\ASUS\\Documents\\NetBeansProjects\\Pidev2\\src\\Images\\drag-drop.gif"));
          logoView.setImage(new Image("file:C:\\Users\\ASUS\\Documents\\NetBeansProjects\\Pidev2\\src\\Images\\logo FitHUb.png"));
    }    

    @FXML
    private void addAction(ActionEvent event) {
        String tit = title.getText();
        String desc =description.getText();
        String type = typePub.getValue();
        stage = new Stage();
        java.sql.Timestamp date_sql = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());  
        
        File image =new File (lab_url.getText());
        Parent root ;
       
        String req = "INSERT INTO `publication2`(`title`, `description`,`image`,`type`,`date_creation`,`topic_id` ) VALUES (?,?,?,?,?,?)";
           if (testSaisie()){
                try {
            st = cnx.prepareStatement(req);
            st.setString(1, tit);
            st.setString(2, desc);
             fs = new FileInputStream(image) ;
             st.setBinaryStream(3, fs, image.length());
             st.setString(4, type);
             st.setObject(5, date_sql);
             st.setInt(6, idCat);
                
            st.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Publication Ajoutee Avec Succees!");
            alert.show();
            root = FXMLLoader.load(getClass().getResource("/GUI/AfficherPublication.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            System.out.println("publication ajoutée avec succes.");
  
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AjouterPublicationController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
              ex.printStackTrace();
            }catch (IOException ex) {
                Logger.getLogger(AjouterPublicationController.class.getName()).log(Level.SEVERE, null, ex);
                    }
           }
           


    }

    @FXML
    private void browse(ActionEvent event) throws MalformedURLException {
         FileChooser fc = new FileChooser();
         fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
        );
        f = fc.showOpenDialog(null);

        if (f != null) {

            lab_url.setText(f.getAbsolutePath());
            Image image=new Image(f.toURI().toString(),imageView.getFitWidth(),imageView.getFitHeight(),true,true);
            imageView.setImage(image);

        }
    }
     private Boolean testTitle() {
        int nbNonChar = 0;
        for (int i = 1; i < title.getText().trim().length(); i++) {
            char ch = title.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && title.getText().trim().length() >= 3) {
            titleCheckMark.setImage(new Image("Images/checkMark.png"));
            return true;
        } else {
            titleCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }
    }
     private Boolean testDesc() {
        int nbNonChar = 0;
        for (int i = 1; i < description.getText().trim().length(); i++) {
            char ch = description.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && description.getText().trim().length() >= 3) {
            descCheckMark.setImage(new Image("Images/checkMark.png"));
            return true;
        } else {
            descCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }

    }
     
     
    
      private Boolean testSaisie() {
        erreur = "";
        
        if (!testTitle()) {
            erreur = erreur + ("Please check the title: only characters and number >= 3 ");
        }
      
          
        

        if (!testTitle()) {
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText(erreur);
            alert.show();
        }

        return testTitle();
    }
    
}
