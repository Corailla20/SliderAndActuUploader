package slideruploader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
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
public class OffreEmploiController implements Initializable {

    
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
    private TableColumn<OffreEmploi,String> columnMetier;
    
    @FXML
    private TableColumn<OffreEmploi,String> columnContrat;
    
    @FXML
    private TableColumn<OffreEmploi,String> columnLieu;
    
    @FXML
    private TableColumn<OffreEmploi,String> columnContenu;    
    
    @FXML
    private TableColumn<OffreEmploi,String> columnNumero;
    
    
    @FXML
    private TableView<OffreEmploi> tableView;
    
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
            
            tableView.setItems(FXCollections.observableArrayList(oracleSQL.getOffres()));
        } catch (SQLException ex) {
            Logger.getLogger(OffreEmploiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        columnMetier.setCellValueFactory(cellData -> cellData.getValue().metierProperty());
        columnContenu.setCellValueFactory(cellData -> cellData.getValue().contenuProperty());
        columnContrat.setCellValueFactory(cellData -> cellData.getValue().contratProperty());
        columnLieu.setCellValueFactory(cellData -> cellData.getValue().lieuProperty());
        columnNumero.setCellValueFactory(cellData -> cellData.getValue().numeroProperty());
        
        buttonAjouter.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Boolean inscription = true;
                if(metierOffre.getText().trim().equals("")){
                    labelSout.setText("Veuillez remplir tous les champs");
                    System.out.println("Métier manquant");
                    inscription = false;
                }
                if(contenuOffre.getText().trim().equals("")){
                    labelSout.setText("Veuillez remplir tous les champs");
                    System.out.println("Contenu manquant");
                    inscription = false;
                }
                if(contratOffre.getText().trim().equals("")){
                    labelSout.setText("Veuillez remplir tous les champs");
                    System.out.println("Contrat manquant");
                    inscription = false;
                }
                if(numeroOffre.getText().trim().equals("")){
                    labelSout.setText("Veuillez remplir tous les champs");
                    System.out.println("Numéro de téléphone manquant");
                    inscription = false;
                }
                if(!contratOffre.getText().trim().equals("CDD") && !contratOffre.getText().trim().equals("CDI") && !contratOffre.getText().trim().equals("Stage") && !contratOffre.getText().trim().equals("Alternance")){
                    labelSout.setText("Contrat incorrect. Contrat possible : CDD, CDI, Stage, Alternance");
                    inscription = false;
                }
                if(lieuOffre.getText().trim().equals("")){
                    labelSout.setText("Veuillez remplir tous les champs");
                    System.out.println("Lieu manquant");
                    inscription = false;
                }
                if(!lieuOffre.getText().trim().equals("63") && !lieuOffre.getText().trim().equals("03") && !lieuOffre.getText().trim().equals("48")){
                    labelSout.setText("Département incorrect. Département possible : 63, 03, 48");
                    inscription = false;
                }
                if(inscription){
                    System.out.println(metierOffre.getText());
                    try {
                        oracleSQL.addOffreInDB(metierOffre.getText(), contratOffre.getText(), lieuOffre.getText(), "0000000", contenuOffre.getText());
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
                    tableView.setItems(FXCollections.observableArrayList(oracleSQL.getOffres()));
                } catch (SQLException ex) {
                        Logger.getLogger(SliderController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        buttonSupprimer.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                OffreEmploi aSelect = tableView.getSelectionModel().getSelectedItem();
                if(aSelect != null){
                    System.out.println(aSelect.getMetier());
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
                          "ModifierOffre.fxml"
                        )
                      );
                Stage newStage = new Stage();
                try {
                    
                    root = loader.load();
                } catch (IOException ex) {
                    Logger.getLogger(OffreEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Scene scene = new Scene(root, 600, 376);
                newStage.setScene(scene);
                newStage.show();
                ModifierOffreController controller = 
                    loader.<ModifierOffreController>getController();
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
        Label ajoutee = new Label("Offre ajoutée");
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
    
    public static void showStage2(OffreEmploi o){
        Stage newStage = new Stage();
        VBox vbox = new VBox();
        HBox hbox = new HBox();
        Label ajoutee = new Label("Êtes-vous sûr(e) de vouloir supprimer cette offre ?");
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
                    oracleSQL.supprimerOffre(o);
                    newStage.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SliderController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
}
