/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Controllers.ListCellCommentsController;
import Entities.Commentaire;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 *
 * @author ASUS
 */
public class TaskCellFactory2 implements Callback<ListView<Commentaire>, ListCell<Commentaire>> {


    @Override
    public ListCell<Commentaire> call(ListView<Commentaire> param) {
       return  new ListCellCommentsController();
    }


    
}
