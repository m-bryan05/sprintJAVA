/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import Models.Admins;
import interfaces.IEvents;
import Models.Events;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utiles.MaConnexion;
/**
 *
 * @author Endezoumou Bryan
 */
public class ServiceEvents implements IEvents {
    
    
    Connection cnx;
    
    public ServiceEvents() {
        cnx = MaConnexion.getInstance().getCnx();
    }

    @Override
    public void ajouterEvents(Events e) {
        try {
            String request = "insert into evenements (titre,categorie,datedebut,datefin,description) values"
                    + " ( '" + e.getTitre()+ "','" + e.getCategorie()+ "','" + e.getDateDebut()+ "','" + e.getDateFin()+ "','" + e.getDescription()+ "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(request);
            System.out.println("Evenement ajouté avec succès");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Events> afficherEvents() {
        List<Events> list = new ArrayList<>();
        try {
            String req ="select * from evenements";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Events e = new Events();
                e.setIdEvent(rs.getInt("Id"));
                e.setTitre(rs.getString("Titre"));
                e.setCategorie(rs.getString("Categorie"));
                e.setDateDebut(rs.getDate("Date Debut"));
                e.setDateFin(rs.getDate("Date Fin"));
                e.setDescription(rs.getString("Description"));
                System.out.println("OK");
                list.add(e);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Events> afficherEvents2() {
        List<Events> list = new ArrayList<>();
        try {
            String req ="select * from evenements";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Events e = new Events();
                e.setIdEvent(rs.getInt("Id"));
                e.setTitre(rs.getString("Titre"));
                e.setCategorie(rs.getString("Categorie"));
                e.setDateDebut(rs.getDate("Date Debut"));
                e.setDateFin(rs.getDate("Date Fin"));
                e.setDescription(rs.getString("Description"));
                list.add(e);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Events> afficherEvents3(String titre) {
        List<Events> list = new ArrayList<>();
        try {
            String req ="select * from evenements where idevent = '"+titre+"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Events e = new Events();
                e.setIdEvent(rs.getInt("Id"));
                e.setTitre(rs.getString("Titre"));
                e.setCategorie(rs.getString("Categorie"));
                e.setDateDebut(rs.getDate("Date Debut"));
                e.setDateFin(rs.getDate("Date Fin"));
                e.setDescription(rs.getString("Description"));
                list.add(e);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public void modifier(Events e) {
        try {
            String req = "update administrateurs set titre = ? , categorie = ? , datedebut = ?, datefin = ?, description = ? where idevent = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, e.getTitre());
            ps.setString(2, e.getCategorie());
            ps.setString(3, String.valueOf(e.getDateDebut()));
            ps.setString(4, String.valueOf(e.getDateFin()));
            ps.setString(5, e.getDescription());
            ps.setInt(6, e.getIdEvent());
            ps.executeUpdate();
            System.out.println("MAJ EFFECTUE");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void modifier2(int idEvent, String titre, String categorie, Date datedebut, Date datefin) {
        try {
            
            PreparedStatement pst = MaConnexion.getInstance().getCnx().prepareStatement("UPDATE evenements SET titre= '"+titre+"', categorie= '"+categorie+"', datedebut= '"+datedebut+"', datefin= '"+datefin+"' WHERE idevent = '"+idEvent+"'");
            pst.executeUpdate();
            System.out.println("evenement modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
    }

    @Override
    public void supprimer(int idEvent) {
        try {
            String req = "delete from evenement where idevent = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, idEvent);
            ps.executeUpdate();
            System.out.println("SUPPRESSION EFFECTUER");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void supprimer2(String idEvent) {
        try {
            String req = "delete from evenement where idevent = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, idEvent);
            ps.executeUpdate();
            System.out.println("SUPPRESSION EFFECTUER");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ObservableList<Events> getAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Events> recherche_titre(String titre) {
        List<Events> list = new ArrayList<>();
        try {
            String req ="select * from evenements where titre = '"+titre+"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Events e = new Events();
              e.setIdEvent(rs.getInt("Id"));
                e.setTitre(rs.getString("Titre"));
                e.setCategorie(rs.getString("Categorie"));
                e.setDateDebut(rs.getDate("Date Debut"));
                e.setDateFin(rs.getDate("Date Fin"));
                e.setDescription(rs.getString("Description"));
                list.add(e);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
}
