/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slideruploader;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Corailla20
 */
public class OffreEmploi {
    StringProperty metierProperty;
    StringProperty contenuProperty;
    StringProperty contratProperty;
    StringProperty lieuProperty;
    StringProperty numeroProperty;

    public OffreEmploi(String metier, String contenu, String contrat, String lieu, String numero) {
        this.metierProperty = new SimpleStringProperty(metier);
        this.contenuProperty = new SimpleStringProperty(contenu);
        this.contratProperty = new SimpleStringProperty(contrat);
        this.lieuProperty = new SimpleStringProperty(lieu);
        this.numeroProperty = new SimpleStringProperty(numero);
    }
    
    public String getContenu() {
        return contenuProperty.get();
    }

    public void setContenu(String contenu) {
        this.contenuProperty = new SimpleStringProperty(contenu);
    }
    
    public String getMetier() {
        return metierProperty.get();
    }

    public void setMetier(String metier) {
        this.metierProperty = new SimpleStringProperty(metier);
    }

    public String getContrat() {
        return contenuProperty.get();
    }

    public void setContrat(String contrat) {
        this.contenuProperty = new SimpleStringProperty(contrat);
    }

    public String getLieu() {
        return lieuProperty.get();
    }

    public void setLieu(String lieu) {
        this.lieuProperty = new SimpleStringProperty(lieu);
    }

    public String getNumero() {
        return numeroProperty.get();
    }

    public void setNumero(String numero) {
        this.numeroProperty = new SimpleStringProperty(numero);
    }
    
    public StringProperty metierProperty() {
        return metierProperty;
    }

    public StringProperty contenuProperty() {
        return contenuProperty;
    }

    public StringProperty contratProperty() {
        return contratProperty;
    }

    public StringProperty lieuProperty() {
        return lieuProperty;
    }    

    public StringProperty numeroProperty() {
        return numeroProperty;
    }
}
