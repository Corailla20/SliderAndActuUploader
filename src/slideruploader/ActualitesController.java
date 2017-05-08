/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slideruploader;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import static slideruploader.SliderController.showStage;
import static slideruploader.SliderController.showStage2;

/**
 * FXML Controller class
 *
 * @author Corailla20
 */
public class ActualitesController implements Initializable {

    
    private static OracleConnector oracleConnector = new OracleConnector();
    private static OracleSQL oracleSQL = new OracleSQL();
    
    @FXML
    private TextField titreActualite;
    
    @FXML
    private TextArea contenuActualite;
    
    @FXML
    private TextField urlActualite;
    
    @FXML
    private TableColumn<Actualite,String> columnTitre;
    
    @FXML
    private TableColumn<Actualite,String> columnContenu;
    
    @FXML
    private TableColumn<Actualite,String> columnURL;
    
    @FXML
    private TableView<Actualite> tableView;
    
    @FXML
    private Button buttonAjouter;
    
    @FXML
    private Button buttonSynchroniser;
    
    @FXML
    private Button buttonSupprimer;
    
    @FXML
    private Button buttonModifier;
    
    @FXML
    private Label labelSout;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            /*tableView.setItems(FXCollections.observableArrayList(
            new Actualite("Fiat 500L","Blbehbeboabge", "http://vujevezvbz.jpg"),
            new Actualite("Alfa Roméo Giulia", "beufhbvozvbouhz", "http://ahuvpaie.jpg"),
            new Actualite("Toyota Aygo", "rzvojhnlhzbvp-s", "http://gajifpav.jpg"),
            new Actualite("Lexus NX", "hobuvejsnlbfhuosnj", "http://vefhpivaz.jpg")));*/
            
            tableView.setItems(FXCollections.observableArrayList(oracleSQL.getActualites()));
        } catch (SQLException ex) {
            Logger.getLogger(ActualitesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        columnTitre.setCellValueFactory(cellData -> cellData.getValue().titreProperty());
        columnContenu.setCellValueFactory(cellData -> cellData.getValue().contenuProperty());
        columnURL.setCellValueFactory(cellData -> cellData.getValue().urlProperty());
        
        buttonAjouter.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {                
                if(titreActualite.getText().trim().equals("")){
                    labelSout.setText("Veuillez remplir tous les champs");
                    System.out.println("Titre manquant");
                }
                if(contenuActualite.getText().trim().equals("")){
                    labelSout.setText("Veuillez remplir tous les champs");
                    System.out.println("Contenu manquant");
                }
                if(urlActualite.getText().trim().equals("")){
                    labelSout.setText("Veuillez remplir tous les champs");
                    System.out.println("URL manquant");
                }
                if(!contenuActualite.getText().trim().equals("") && !titreActualite.getText().trim().equals("") && !urlActualite.getText().trim().equals("")){
                    System.out.println(titreActualite.getText());
                    try {
                        oracleSQL.addActualiteInDB(titreActualite.getText(), contenuActualite.getText(),urlActualite.getText());
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
                    tableView.setItems(FXCollections.observableArrayList(oracleSQL.getActualites()));
                } catch (SQLException ex) {
                        Logger.getLogger(SliderController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        buttonSupprimer.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Actualite aSelect = tableView.getSelectionModel().getSelectedItem();
                if(aSelect != null){
                    System.out.println(aSelect.getTitre());
                    showStage2(aSelect);
                }
                else {
                    System.out.println("Aucun élément séléctionné");
                    labelSout.setText("Aucun élément séléctionné");
                }
            }
        });       
        
        buttonModifier.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                if(tableView.getSelectionModel().getSelectedItem() != null) {
                Parent root = null;
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource(
                          "ModifierActu.fxml"
                        )
                      );
                Stage newStage = new Stage();
                try {
                    
                    root = loader.load();
                } catch (IOException ex) {
                    Logger.getLogger(ActualitesController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Scene scene = new Scene(root, 600, 323);
                newStage.setScene(scene);
                newStage.show();
                ModifierActuController controller = 
                    loader.<ModifierActuController>getController();
                  controller.initData(tableView.getSelectionModel().getSelectedItem());
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
        Label ajoutee = new Label("Actualité ajoutée");
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
    
    public static void showStage2(Actualite a){
        Stage newStage = new Stage();
        VBox vbox = new VBox();
        HBox hbox = new HBox();
        Label ajoutee = new Label("Êtes-vous sûr(e) de vouloir supprimer cette bannière ?");
        Button buttonOui = new Button("Oui");
        Button buttonAnnuler = new Button("Annuler");
        
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().add(buttonOui);
        hbox.getChildren().add(buttonAnnuler);
        
        hbox.setMargin(buttonOui,new Insets(10,20,0,0));
        hbox.setMargin(buttonAnnuler,new Insets(10,0,0,0));
        
        vbox.setAlignment(Pos.CENTER);
        
        vbox.getChildren().add(ajoutee);
        vbox.getChildren().add(hbox);
        

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
                    oracleSQL.supprimerActualite(a);
                    newStage.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SliderController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
}
