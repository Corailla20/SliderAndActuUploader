package slideruploader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import static slideruploader.ActualitesController.showStage;

/**
 * FXML Controller class
 *
 * @author Corailla20
 */
public class ModifierActuController implements Initializable {
    
    private static OracleConnector oracleConnector = new OracleConnector();
    private static OracleSQL oracleSQL = new OracleSQL();
    
    @FXML
    private TextField titreActualite;
    
    @FXML
    private TextArea contenuActualite;
    
    @FXML
    private TextField urlActualite;
    
    @FXML
    private Button buttonAnnulerModif;
    
    @FXML
    private Button buttonValiderModif;
    
    @FXML
    private Label labelSout;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        
    }    
    
    
  void initData(Actualite actu) {
    titreActualite.setText(actu.getTitre());
    urlActualite.setText(actu.getUrl());
    contenuActualite.setText(actu.getContenu());
    
    buttonAnnulerModif.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                ((Node)(e.getSource())).getScene().getWindow().hide();
            }
        });
        
        buttonValiderModif.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                   
                if(titreActualite.getText().trim().equals("")){
                    System.out.println("Titre manquant");
                    labelSout.setText("Veuillez remplir tous les champs");
                }
                if(contenuActualite.getText().trim().equals("")){
                    System.out.println("Contenu manquant");
                    labelSout.setText("Veuillez remplir tous les champs");
                }
                if(urlActualite.getText().trim().equals("")){
                    System.out.println("URL manquant");
                    labelSout.setText("Veuillez remplir tous les champs");
                }
                if(!contenuActualite.getText().trim().equals("") && !titreActualite.getText().trim().equals("") && !urlActualite.getText().trim().equals("")){
                    Actualite newActu = new Actualite(titreActualite.getText(), contenuActualite.getText(), urlActualite.getText());
                    try {
                        oracleSQL.modifierActu(actu.getTitre(),newActu);
                    } catch (SQLException ex) {
                        Logger.getLogger(ModifierActuController.class.getName()).log(Level.SEVERE, null, ex);
                    }                
                    ((Node)(e.getSource())).getScene().getWindow().hide();
                }
            }
        });
  }
    
}
