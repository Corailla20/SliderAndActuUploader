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
public class Banniere {
    StringProperty nomProperty;
    StringProperty urlProperty;

    public Banniere(String nom, String url) {
        this.nomProperty = new SimpleStringProperty(nom);
        this.urlProperty = new SimpleStringProperty(url);
    }
    
    public String getNom() {
        return nomProperty.get();
    }

    public void setNom(String nom) {
        this.nomProperty = new SimpleStringProperty(nom);
    }

    public String getUrl() {
        return urlProperty.get();
    }

    public void setUrl(String url) {
        this.urlProperty = new SimpleStringProperty(url);
    }
    
    public StringProperty nomProperty() {
        return nomProperty;
    }    
    
    public StringProperty urlProperty() {
        return urlProperty;
    }
}
