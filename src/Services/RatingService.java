/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.RatingEx;
import Utils.MaConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author wassi
 */
public class RatingService {
    Connection cnx = MaConnexion.getInstance().getCnx();
   
  
    public void ajouter(RatingEx r) {
      String query = "INSERT INTO rating(id_exercice,rate) VALUES(?,?)";
        try{
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setInt(1,r.getIdExercice());
            ste.setInt(2,r.getRate());
            ste.executeUpdate();
            System.out.println("Rate Added Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
