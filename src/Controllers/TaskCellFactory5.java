/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Topic;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 *
 * @author ASUS
 */
public class TaskCellFactory5 implements Callback<ListView<Topic>, ListCell<Topic>> {


    @Override
    public ListCell<Topic> call(ListView<Topic> param) {
       return  new ListCellSuggetionsTopics();
    }


    
}
