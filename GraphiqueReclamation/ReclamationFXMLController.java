/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphiqueReclamation;

import Entite.Reclamation;
import Service.ServiceReclamation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Yassine Karoui
 */
public class ReclamationFXMLController implements Initializable {

    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnSignout;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private TextField tf_recherche;
    @FXML
    private Button btn_trier;
    @FXML
    private AnchorPane Ajouter1;
    @FXML
    private Button btn_ajouter;
    @FXML
    private TextField fxObjet;
    @FXML
    private TextArea fxReclamation;
    @FXML
    private Pane pnlOrders;
    @FXML
    private TableView<Reclamation> tableReclamation;
    @FXML
    private TableColumn<?, ?> fxref;
    @FXML
    private TableColumn<?, ?> fxobjet;
    @FXML
    private TableColumn<?, ?> fxmessage;
    @FXML
    private Button fximprimer;
    @FXML
    private Button btnProfil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleClicks(ActionEvent event) throws IOException {
                 
        if (event.getSource() == btnCustomers) {
            pnlCustomer.setStyle("-fx-background-color : #D3D3D3");
            pnlCustomer.toFront();
            Stage stage = (Stage) tf_recherche.getScene().getWindow();
            stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GraphiqueCours/Home.fxml"));
            Parent root = loader.load();
            Stage stageInscription= new Stage();
            stageInscription.initStyle(StageStyle.UNDECORATED);
            stageInscription.setScene(new Scene(root, 1050, 576));
            stageInscription.show(); 
            
        }
        if (event.getSource() == btnMenus) {
             //pnlMenus.setStyle("-fx-background-color : #D3D3D3");
            //pnlMenus.toFront();
            Stage stage = (Stage) tf_recherche.getScene().getWindow();
            stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GraphiqueSalleDeSport/SalleDeSport.fxml"));
            Parent root = loader.load();
            Stage stageInscription= new Stage();
            stageInscription.initStyle(StageStyle.UNDECORATED);

            stageInscription.setScene(new Scene(root, 1050, 576));
            stageInscription.show(); 
        }
        if (event.getSource() == btnOverview) {
            //pnlOverview.setStyle("-fx-background-color : #D3D3D3");
            //pnlOverview.toFront();
            Stage stage = (Stage) tf_recherche.getScene().getWindow();
            stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GraphiqueProduits/Produits.fxml"));
            Parent root = loader.load();
            Stage stageInscription1= new Stage();
            stageInscription1.initStyle(StageStyle.UNDECORATED);
            stageInscription1.setScene(new Scene(root, 1050, 576));
            stageInscription1.show();
        }
        if(event.getSource()==btnOrders)
        {
            pnlOrders.setStyle("-fx-background-color : #D3D3D3");
            pnlOrders.toFront();
            
        }
        if(event.getSource()==btnPackages)
        {
            pnlOrders.setStyle("-fx-background-color : #D3D3D3");
            pnlOrders.toFront();
            Stage stage = (Stage) tf_recherche.getScene().getWindow();
            stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GraphiqueCalendrier/Calendrier.fxml"));
            Parent root = loader.load();
            Stage stageInscription1= new Stage();
            stageInscription1.initStyle(StageStyle.UNDECORATED);
            stageInscription1.setScene(new Scene(root, 1050, 576));
            stageInscription1.show();
            
        }
        
        if(event.getSource()==btnSignout)
        {
            pnlOrders.setStyle("-fx-background-color : #D3D3D3");
            pnlOrders.toFront();
            Stage stage = (Stage) tf_recherche.getScene().getWindow();
            stage.close();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GraphiqueUser/InterfaceIA.fxml"));
            Parent root = loader.load();
            Stage stageInscription1= new Stage();
            stageInscription1.initStyle(StageStyle.UNDECORATED);
            stageInscription1.setScene(new Scene(root, 1050, 576));
            stageInscription1.show();
            
        }
        
    }


    @FXML
    private void rechercherCours(KeyEvent event) {
    }

    @FXML
    private void TrierCours(ActionEvent event) {
    }

    @FXML
    private void afficherrrr(Event event) {
        ServiceReclamation rec = new ServiceReclamation();
        ObservableList<Reclamation> items = FXCollections.observableArrayList(rec.afficherReclamation());
       
           fxref.setCellValueFactory(new PropertyValueFactory("ref"));
           fxobjet.setCellValueFactory(new PropertyValueFactory("Objet"));
           fxmessage.setCellValueFactory(new PropertyValueFactory("MSG"));
        

           tableReclamation.setItems(items);
        
    }

    @FXML
    private void ajouterReclamation(ActionEvent event) {
        ServiceReclamation sr = new ServiceReclamation() ;
        sr.ajouterReclamation(new Reclamation(fxObjet.getText(),fxReclamation.getText()));
        JOptionPane.showMessageDialog(null,"Votre reclamation a ete envoye");    }

   

    @FXML
    private void BPDF(ActionEvent event) {
        System.out.println("To Printer!");
        PrinterJob job = PrinterJob.createPrinterJob();
        if(job != null){
        Window primaryStage = null;
        job.showPrintDialog(primaryStage);     
        Node root = this.tableReclamation;
        job.printPage(root);
        job.endJob();
    }   
    }
    }

   

