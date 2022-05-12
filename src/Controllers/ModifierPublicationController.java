/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Publication;
import Controllers.ListCellCommande;
import static Controllers.AjouterPublicationController.stage;
import Services.PublicationServices;
import Services.TopicServices;
import Utils.MaConnexion;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.ByteArrayInputStream;
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
public class ModifierPublicationController implements Initializable {
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
    
    
    @FXML
    private ComboBox<String> typePub;
    ObservableList<String> listTypes = FXCollections.observableArrayList(tps.getNomTopics());
    
    @FXML
    private Button lab_url;
    private FileInputStream fs ;
    @FXML
    private ImageView logoView;
    byte byteImg[] ;
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          cnx = MaConnexion.getInstance().getCnx();
        // TODO
        PublicationServices ps = new PublicationServices();
            title.setText(AfficherPublicationController.selectionedPub.getTitle());
            description.setText(AfficherPublicationController.selectionedPub.getDescription());
            typePub.setValue(AfficherPublicationController.selectionedPub.getType());
             try {
                byteImg=AfficherPublicationController.selectionedPub.getImage().getBytes(1, (int)AfficherPublicationController.selectionedPub.getImage().length());
                Image img = new Image(new ByteArrayInputStream(byteImg),imageView.getFitHeight(),imageView.getFitHeight(),true,true) ;
                imageView.setImage(img);
                
                //imageView.setImage(new Image(publication.getImage())); 
            } catch (SQLException ex) {
                Logger.getLogger(ListCellCommande.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            

          logoView.setImage(new Image("file:C:\\Users\\ASUS\\Documents\\NetBeansProjects\\Pidev2\\src\\Images\\logo FitHUb.png"));
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

    @FXML
    private void update(ActionEvent event) {
     
     
        stage = new Stage();
        java.sql.Timestamp date_sql = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());  
        
        File image =new File (lab_url.getText());
        Parent root ;
        String req ="UPDATE publication2 set title=?, type=? ,description=? ,image=?,date_creation=? WHERE id=?";
         try {
             st = cnx.prepareStatement(req);
             st.setString(1, title.getText());
             st.setString(2, typePub.getValue());
             st.setString(3, description.getText());
             
             fs = new FileInputStream(image) ;
       
       
             st.setBinaryStream(4, fs, image.length());
             
             st.setObject(5, date_sql);
             st.setInt(6, AfficherPublicationController.selectionedPub.getId());
            st.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Publication Modifier Avec Succees!");
            alert.show();
            root = FXMLLoader.load(getClass().getResource("/GUI/AfficherPublication.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            System.out.println("publication Modifier avec succes.");
  
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AjouterPublicationController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
              ex.printStackTrace();
            }catch (IOException ex) {
                Logger.getLogger(AjouterPublicationController.class.getName()).log(Level.SEVERE, null, ex);
                    }
    }
    
}
