/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Models.Admins;
import interfaces.IAdmins;
import java.sql.Connection;
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
public class ServiceAdmins implements IAdmins {
    
    Connection cnx;
    
    public ServiceAdmins() {
        cnx = MaConnexion.getInstance().getCnx();
    }

    @Override
    public void ajouterAdmins(Admins a) {
        try {
            String request = "insert into administrateurs (nom,prenom,mail,tel,adresse) values"
                    + " ( '" + a.getName()+ "','" + a.getSurname()+ "','" + a.getMail()+ "','" + a.getTel()+ "','" + a.getAdresse()+ "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(request);
            System.out.println("Administrateur ajouté avec succès");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Admins> afficherAdmins() {
        List<Admins> list = new ArrayList<>();
        try {
            String req ="select * from administrateurs";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Admins a = new Admins();
                a.setId(rs.getInt("Id"));
                a.setName(rs.getString("Nom"));
                a.setSurname(rs.getString("Prenom"));
                a.setMail(rs.getString("Mail"));
                a.setTel(rs.getInt("Tel"));
                a.setAdresse(rs.getString("Adresse"));
                System.out.println("OK");
                list.add(a);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Admins> afficherAdmins2() {
        List<Admins> list = new ArrayList<>();
        try {
            String req ="select * from administrateurs";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Admins a = new Admins();
                a.setId(rs.getInt(1));
                a.setName(rs.getString("nom"));
                a.setSurname(rs.getString("prenom"));
                a.setMail(rs.getString("mail"));
                a.setTel(rs.getInt("tel"));
                a.setAdresse(rs.getString("Adresse"));
                list.add(a);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Admins> afficherAdmins3(String nom) {
        List<Admins> list = new ArrayList<>();
        try {
            String req ="select * from admins where id = '"+nom+"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Admins a = new Admins();
                a.setId(rs.getInt(1));
                a.setName(rs.getString("nom"));
                a.setSurname(rs.getString("prenom"));
                a.setMail(rs.getString("mail"));
                a.setTel(rs.getInt("tel"));
                a.setAdresse(rs.getString("Adresse"));
                list.add(a);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public void modifier(Admins a) {
        try {
            String req = "update administrateurs set nom = ? , prenom = ? , mail = ?, tel = ?, adresse = ? where id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, a.getName());
            ps.setString(2, a.getSurname());
            ps.setString(3, a.getMail());
            ps.setInt(4, a.getTel());
            ps.setString(5, a.getAdresse());
            ps.setInt(6, a.getId());
            ps.executeUpdate();
            System.out.println("MAJ EFFECTUE");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void modifier2(int id, String name, String surname, String mail, int tel, String adresse) {
        try {
            
            PreparedStatement pst = MaConnexion.getInstance().getCnx().prepareStatement("UPDATE administrateurs SET name= '"+name+"', surname= '"+surname+"', mail= '"+mail+"', tel= '"+tel+"', adresse= '"+adresse+"' WHERE id = '"+id+"'");
            pst.executeUpdate();
            System.out.println("menu modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "delete from administrateurs where id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("SUPPRESSION EFFECTUER");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void supprimer2(String id) {
        try {
            String req = "delete from administrateurs where id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, id);
            ps.executeUpdate();
            System.out.println("SUPPRESSION EFFECTUER");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    

    @Override
    public List<Admins> recherche_nom(String nom_rechercher) {
        List<Admins> list = new ArrayList<>();
        try {
            String req ="select * from administrateurs where nom = '"+nom_rechercher+"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Admins a = new Admins();
                a.setId(rs.getInt(1));
                a.setName(rs.getString("nom"));
                a.setSurname(rs.getString("prenom"));
                a.setMail(rs.getString("mail"));
                a.setTel(rs.getInt("telephone"));
                list.add(a);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public ObservableList<Admins> getAll() throws SQLException {
        ObservableList <Admins> list = FXCollections.observableArrayList();
        // String req = "select * from order o inner join user u on u.id=o.id_user";
        String req = "select * from administrateurs ";

        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
      
            while (rs.next()){
                list.add(new Admins(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("mail"),rs.getInt("tel"),rs.getString("adresse")));
            }
        }catch (SQLException err){
            System.out.println(err.getMessage());
        }

        return list;
    }

    public void modifier2(String nom_admins, String prenom_admins, String mail_admins, int tel_admins, String adresse_admins) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
