/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import Entities.Publication;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 *
 * @author ASUS
 */
public class TaskCellFactory3 implements Callback<ListView<Publication>, ListCell<Publication>> {


    @Override
    public ListCell<Publication> call(ListView<Publication> param) {
       return  new ListCellPopularNews();
    }


    
}
