/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Endezoumou Bryan
 */
public class Admins {
    
    
    int id ;
    String name;
    String surname;
    String mail;
    int tel;
    String adresse;
    
    public Admins(){
        
    }

    public Admins(int id, String name, String surname, String mail, int tel, String adresse) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.tel = tel;
        this.adresse = adresse;
    }

    public Admins(String name, String surname, String mail, int tel, String adresse) {
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.tel = tel;
        this.adresse = adresse;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getMail() {
        return mail;
    }

    public int getTel() {
        return tel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
    
    
}
