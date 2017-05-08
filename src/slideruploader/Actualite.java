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
public class Actualite {
    StringProperty titreProperty;
    StringProperty contenuProperty;
    StringProperty urlProperty;

    public Actualite(String titre, String contenu, String url) {
        this.titreProperty = new SimpleStringProperty(titre);
        this.contenuProperty = new SimpleStringProperty(contenu);
        this.urlProperty = new SimpleStringProperty(url);
    }
    
    public String getContenu() {
        return contenuProperty.get();
    }

    public void setContenu(String contenu) {
        this.contenuProperty = new SimpleStringProperty(contenu);
    }
    
    public String getTitre() {
        return titreProperty.get();
    }

    public void setTitre(String nom) {
        this.titreProperty = new SimpleStringProperty(nom);
    }

    public String getUrl() {
        return urlProperty.get();
    }

    public void setUrl(String url) {
        this.urlProperty = new SimpleStringProperty(url);
    }
    
    public StringProperty titreProperty() {
        return titreProperty;
    }    
    
    public StringProperty urlProperty() {
        return urlProperty;
    }
    
    public StringProperty contenuProperty() {
        return contenuProperty;
    }
}
