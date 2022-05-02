package Controllers;


import Models.Admins;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class    ListCellCommande extends ListCell<Admins> {
    @FXML
    private AnchorPane AdminCell;

    @FXML
    private Label adresse;

    @FXML
    private Label email;

    @FXML
    private Label nom;

    @FXML
    private Label prenom;

    @FXML
    private Label tel;

    private FXMLLoader mLLoader;

    @Override
    protected void updateItem(Admins student, boolean empty) {
        super.updateItem(student, empty);

        if(empty || student == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/Views/ListCellCommande.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            

       
            nom.setText(student.getName());
            prenom.setText(student.getSurname());
            email.setText(student.getMail());
            adresse.setText(student.getAdresse());
            tel.setText(String.valueOf(student.getTel()));
            

            setText(null);
            setGraphic(AdminCell);
        }
    }

}
