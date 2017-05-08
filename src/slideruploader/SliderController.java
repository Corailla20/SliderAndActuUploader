/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slideruploader;

import java.awt.Image;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Corailla20
 */
public class SliderController implements Initializable {
    
    private static OracleConnector oracleConnector = new OracleConnector();
    private static OracleSQL oracleSQL = new OracleSQL();
    
    @FXML
    private TextField nomBanniere;
    
    @FXML
    private TextField urlBanniere;
    
    @FXML
    private TableColumn<Banniere,String> columnNom;
    
    @FXML
    private TableColumn<Banniere,String> columnURL;
    
    @FXML
    private TableView<Banniere> tableView;
    
    @FXML
    private Button buttonAjouter;
    
    @FXML
    private Button buttonSynchroniser;
    
    @FXML
    private Button buttonSupprimer;
    
    @FXML
    private Label labelSout;
    
    /*@FXML
    private void handleButtonAction(ActionEvent event) throws InterruptedException, SQLException {
        System.out.println(nomBanniere.getText().toString() + " : " + urlBanniere.getText().toString());
        //label.setText("Bannière ajoutée !");
        oracleSQL.addBanniereInDB(nomBanniere.getText().toString(), urlBanniere.getText().toString());
        showStage();
        
    }*/
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        try {
            /*tableView.setItems(FXCollections.observableArrayList(
            new Banniere("Fiat 500L", "http://vujevezvbz.jpg"),
            new Banniere("Alfa Roméo Giulia", "http://ahuvpaie.jpg"),
            new Banniere("Toyota Aygo", "http://gajifpav.jpg"),
            new Banniere("Lexus NX", "http://vefhpivaz.jpg")));*/
            
            tableView.setItems(FXCollections.observableArrayList(oracleSQL.getBannieres()));
        } catch (SQLException ex) {
            Logger.getLogger(SliderController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /*columnNom.setCellValueFactory(
        new PropertyValueFactory<Banniere, String>("nom"));
        columnURL.setCellValueFactory(
        new PropertyValueFactory<Banniere, String>("url"));*/
        columnNom.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
        columnURL.setCellValueFactory(cellData -> cellData.getValue().urlProperty());
        
        
        buttonAjouter.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                if(nomBanniere.getText().trim().equals("")){
                    System.out.println("Nom manquant");
                    labelSout.setText("Veuillez remplir tous les champs");
                }
                if(urlBanniere.getText().trim().equals("")){
                    System.out.println("URL manquant");
                    labelSout.setText("Veuillez remplir tous les champs");
                }
                if(!nomBanniere.getText().trim().equals("") && !urlBanniere.getText().trim().equals("")){
                    System.out.println(nomBanniere.getText() + " : " + urlBanniere.getText().toString());
                    try {
                        oracleSQL.addBanniereInDB(nomBanniere.getText(), urlBanniere.getText().toString());
                    } catch (SQLException ex) {
                        Logger.getLogger(SliderController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    showStage();
                }
            }
        });
        
        buttonSynchroniser.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                try {
                    tableView.setItems(FXCollections.observableArrayList(oracleSQL.getBannieres()));
                } catch (SQLException ex) {
                        Logger.getLogger(SliderController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        buttonSupprimer.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Banniere bSelect = tableView.getSelectionModel().getSelectedItem();
                if(bSelect != null){
                    System.out.println(bSelect.getNom());
                    showStage2(bSelect);
                }
                else {
                    System.out.println("Aucun élément séléctionné");
                    labelSout.setText("Aucun élément séléctionné");
                }
            }
        });                
    }    
    
    public static void showStage(){
        Stage newStage = new Stage();
        VBox comp = new VBox();
        Label ajoutee = new Label("Bannière ajoutée");
        Button buttonOk = new Button("OK");
        
        comp.setAlignment(Pos.CENTER);
        
        comp.getChildren().add(ajoutee);
        comp.getChildren().add(buttonOk);      

        Scene stageScene = new Scene(comp, 200, 50);
        newStage.setScene(stageScene);
        newStage.show();
        
        
        buttonOk.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                newStage.close();
                
            }
        });
    }
    
    public static void showStage2(Banniere b){
        Stage newStage = new Stage();
        VBox vbox = new VBox();
        HBox hbox = new HBox();
        Label ajoutee = new Label("Êtes-vous sûr(e) de vouloir supprimer cette bannière ?");
        Button buttonOui = new Button("Oui");
        Button buttonAnnuler = new Button("Annuler");
        
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().add(buttonOui);
        hbox.getChildren().add(buttonAnnuler);
        
        vbox.setAlignment(Pos.CENTER);
        
        vbox.getChildren().add(ajoutee);
        vbox.getChildren().add(hbox);
        
        hbox.setMargin(buttonOui,new Insets(10,20,0,0));
        hbox.setMargin(buttonAnnuler,new Insets(10,0,0,0));
        

        Scene stageScene = new Scene(vbox, 300, 70);
        newStage.setScene(stageScene);
        newStage.show();
        
        
        buttonAnnuler.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                newStage.close();
            }
        });
        buttonOui.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {                
                try {
                    oracleSQL.supprimerBanniere(b);
                    newStage.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SliderController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
}
