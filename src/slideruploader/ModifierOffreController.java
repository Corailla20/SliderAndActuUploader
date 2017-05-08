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
public class ModifierOffreController implements Initializable {
    
    private static OracleConnector oracleConnector = new OracleConnector();
    private static OracleSQL oracleSQL = new OracleSQL();
    
    @FXML
    private TextField metierOffre;
    
    @FXML
    private TextArea contenuOffre;
    
    @FXML
    private TextField contratOffre;
    
    @FXML
    private TextField lieuOffre;
    
    @FXML
    private TextField numeroOffre;
    
    @FXML
    private Button buttonAnnulerModif;
    
    @FXML
    private Button buttonValiderModif;
    
    @FXML
    private Label labelSout;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        
    }    
    
    
  void initData(OffreEmploi offre) {
    metierOffre.setText(offre.getMetier());
    lieuOffre.setText(offre.getLieu());
    contratOffre.setText(offre.getContrat());
    contenuOffre.setText(offre.getContenu());
    numeroOffre.setText(offre.getNumero());
    
    buttonAnnulerModif.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                ((Node)(e.getSource())).getScene().getWindow().hide();
            }
        });
        
        buttonValiderModif.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Boolean modif = true;
                if(metierOffre.getText().trim().equals("")){
                    System.out.println("Métier manquant");
                    labelSout.setText("Veuillez remplir tous les champs");
                    modif = false;
                }
                if(lieuOffre.getText().trim().equals("")){
                    System.out.println("Lieu manquant");
                    labelSout.setText("Veuillez remplir tous les champs");
                    modif = false;
                }
                if(contratOffre.getText().trim().equals("")){
                    System.out.println("Contrat manquant");
                    labelSout.setText("Veuillez remplir tous les champs");
                    modif = false;
                }
                if(contenuOffre.getText().trim().equals("")){
                    System.out.println("Contenu manquant");
                    labelSout.setText("Veuillez remplir tous les champs");
                    modif = false;
                }
                if(numeroOffre.getText().trim().equals("")){
                    System.out.println("Téléphone manquant");
                    labelSout.setText("Veuillez remplir tous les champs");
                    modif = false;
                }
                if(modif){
                    OffreEmploi newOffre = new OffreEmploi(metierOffre.getText().trim(), contenuOffre.getText().trim(), contratOffre.getText().trim(),lieuOffre.getText().trim(), numeroOffre.getText());
                    try {
                        oracleSQL.modifierOffre(offre.getContenu(),newOffre);
                    } catch (SQLException ex) {
                        Logger.getLogger(ModifierOffreController.class.getName()).log(Level.SEVERE, null, ex);
                    }                
                    ((Node)(e.getSource())).getScene().getWindow().hide();
                }
            }
        });
  }
    
}
