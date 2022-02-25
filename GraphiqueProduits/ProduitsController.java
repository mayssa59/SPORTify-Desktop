/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphiqueProduits;

import Entite.Cours;
import Entite.Produits;
import Entite.SalleDeSport;
import Service.ProduitsCrud;
import Service.ServiceCours;
import Service.ServiceSalleDeSport;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.textfield.TextFields;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Yassine Karoui
 */
public class ProduitsController implements Initializable {

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
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> type;
    @FXML
    private TableColumn<?, ?> quantites;
    @FXML
    private TableColumn<?, ?> prix;
    @FXML
    private AnchorPane Ajouter1;
    @FXML
    private TextField tf_quantites;
    @FXML
    private TextField tf_prix;
    @FXML
    private TextField tf_type;
    @FXML
    private TextField tf_id;
    @FXML
    private Button btn_ajouter;
    @FXML
    private TextField tf_libelle;
    @FXML
    private AnchorPane Modifier;
    @FXML
    private TextField tf_type1;
    @FXML
    private TextField tf_id1;
    @FXML
    private Button btn_modifier;
    @FXML
    private TextField tf_libelle1;
    @FXML
    private TextField tf_prix1;
    @FXML
    private AnchorPane Supprimer;
    @FXML
    private Button btn_supprimer;
    private Pane pnlOverview;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOrders;
    @FXML
    private TableColumn<?, ?> libelle;
    @FXML
    private TableView<Produits> ListeProduits;
     
    ArrayList name = new ArrayList();
        Connection cnx = Utils.DataSource.getInstance().getCnx();
         ObservableList<Produits> list = FXCollections.observableArrayList();
    @FXML
    private TextField tf_quantites1;
    @FXML
    private ComboBox<String> CB_Supprimer;
    @FXML
    private TextField rechercheee;
    @FXML
    private BorderPane pane;
    @FXML
    private AnchorPane stat;

    
    private final ObservableList <PieChart.Data> details =FXCollections.observableArrayList();
        private PieChart piechart;
    @FXML
    private Button btnProfil;
    
         

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ResultSet rs = null;
         
        try {
            rs = cnx.createStatement().executeQuery("SELECT * FROM produits");
        } catch (SQLException ex) {
            Logger.getLogger(ProduitsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while(rs.next()){
                try {
                    list.add(new Produits(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5)));
                } catch (SQLException ex) {
                    Logger.getLogger(ProduitsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                id.setCellValueFactory(new PropertyValueFactory<>("id"));
          libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
          type.setCellValueFactory(new PropertyValueFactory<>("type"));
          quantites.setCellValueFactory(new PropertyValueFactory<>("quantites"));
          prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
          
                
                ListeProduits.setItems(list);
                
                ListeProduits.refresh();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } 
         
 ResultSet rs8 = null;
        try {
            rs8 = cnx.createStatement().executeQuery("SELECT libelle FROM produits");
        } catch (SQLException ex) {
            Logger.getLogger(ProduitsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while(rs8.next()){
                name.add(rs8.getString("libelle"));
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
                 TextFields.bindAutoCompletion(tf_recherche,name); 
                 
         tf_recherche.getText();

        
        FilteredList<Produits> filteredData = new FilteredList<>(list, b -> true);
        tf_recherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Utilisateur -> {
                            if (newValue == null || newValue.isEmpty()) {
					return true;
                        }
        String lowerCaseFilter = newValue.toLowerCase();
				
				if (Utilisateur.getLibelle().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (Utilisateur.getLibelle().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(Utilisateur.getLibelle()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; 
                                });
                        
		});
        
        SortedList<Produits> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(ListeProduits.comparatorProperty());
        ListeProduits.setItems(sortedData); 
                    ProduitsCrud ss = new ProduitsCrud();

    ObservableList<String> Libelle = FXCollections.observableArrayList(ss.afficherALLLibelle());
       CB_Supprimer.setItems(Libelle);
    
    }    

    @FXML
    private void handleClicks(ActionEvent event) throws IOException {
        /*
        ProduitsCrud ss = new ProduitsCrud();
        ObservableList<Produits> items = FXCollections.observableArrayList(ss.afficherProduits());
       
           id.setCellValueFactory(new PropertyValueFactory("id"));
           libelle.setCellValueFactory(new PropertyValueFactory("libelle"));
           type.setCellValueFactory(new PropertyValueFactory("type"));
           quantites.setCellValueFactory(new PropertyValueFactory("quantites"));
           prix.setCellValueFactory(new PropertyValueFactory("prix"));
           
           ListeProduits.setItems(items); */
              
                
                
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
            pnlMenus.setStyle("-fx-background-color : #D3D3D3");
            pnlMenus.toFront();
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
        }
        if(event.getSource()==btnOrders)
        {
            pnlOrders.setStyle("-fx-background-color : #D3D3D3");
            pnlOrders.toFront();
            Stage stage = (Stage) tf_recherche.getScene().getWindow();
            stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GraphiqueReclamation/ReclamationFXML.fxml"));
            Parent root = loader.load();
            Stage stageInscription= new Stage();
            stageInscription.initStyle(StageStyle.UNDECORATED);
            stageInscription.setScene(new Scene(root, 1050, 576));
            stageInscription.show(); 
            
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
                ProduitsCrud ss = new ProduitsCrud();

        tf_recherche.getText();

        
        FilteredList<Produits> filteredData = new FilteredList<>(list, b -> true);
        tf_recherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Utilisateur -> {
                            if (newValue == null || newValue.isEmpty()) {
					return true;
                        }
        String lowerCaseFilter = newValue.toLowerCase();
				
				if (Utilisateur.getLibelle().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (Utilisateur.getLibelle().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(Utilisateur.getLibelle()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; 
                                });
                        
		});
        
        SortedList<Produits> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(ListeProduits.comparatorProperty());
        ListeProduits.setItems(sortedData);
        ObservableList<String> Libelle = FXCollections.observableArrayList(ss.afficherALLLibelle());
       CB_Supprimer.setItems(Libelle);
    }

    @FXML
    private void TrierCours(ActionEvent event) {
    }

    @FXML
    private void afficherrrr(Event event) {
        ProduitsCrud ss = new ProduitsCrud();
        ObservableList<Produits> items = FXCollections.observableArrayList(ss.afficherProduits());
        
           
           id.setCellValueFactory(new PropertyValueFactory<>("id"));
          libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
          type.setCellValueFactory(new PropertyValueFactory<>("type"));
          quantites.setCellValueFactory(new PropertyValueFactory<>("quantites"));
          prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
           

           ListeProduits.setItems(items); 
           
       
    }

    @FXML
    private void AjouterProduit(ActionEvent event) {
      ProduitsCrud ss = new ProduitsCrud();
        
        if (tf_id.getText().isEmpty()||tf_libelle.getText().isEmpty()||tf_type.getText().isEmpty()||tf_quantites.getText().isEmpty()||tf_prix.getText().isEmpty())
        {
             Alert alert = new Alert(Alert.AlertType.ERROR); 
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();
        }
        else{
   
        ss.ajouterProduits(new Produits(Integer.parseInt(tf_id.getText()),tf_libelle.getText(),tf_type.getText(),Integer.parseInt(tf_quantites.getText()),Integer.parseInt(tf_prix.getText())));
         JOptionPane.showMessageDialog(null,"Produit ajoutée");
         clean();
         ObservableList<String> Libelle = FXCollections.observableArrayList(ss.afficherALLLibelle());
       CB_Supprimer.setItems(Libelle);
        }
    }
    

    @FXML
    private void ModifierProduit(ActionEvent event) {
        ProduitsCrud ss = new ProduitsCrud();
         if (tf_id1.getText().isEmpty()||tf_libelle1.getText().isEmpty()||tf_type1.getText().isEmpty()||tf_quantites1.getText().isEmpty()||tf_prix1.getText().isEmpty())
        {
             Alert alert = new Alert(Alert.AlertType.ERROR); 
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();
        }
        else{
        ss.modifierProduits(new Produits(Integer.parseInt(tf_id1.getText()),tf_libelle1.getText(),tf_type1.getText(),Integer.parseInt(tf_quantites1.getText()),Integer.parseInt(tf_prix1.getText())));
             System.out.println(new Produits(Integer.parseInt(tf_id1.getText()),tf_libelle1.getText(),tf_type1.getText(),Integer.parseInt(tf_quantites1.getText()),Integer.parseInt(tf_prix1.getText()))); 
        JOptionPane.showMessageDialog(null,"Produit modifiée");
         clean();
         ObservableList<String> libelle = FXCollections.observableArrayList(ss.afficherALLLibelle());
      CB_Supprimer.setItems(libelle);
     
       
       
         }
    }

    @FXML
    private void SupprimerProduit(ActionEvent event) {
            ProduitsCrud ss = new ProduitsCrud();
        ss.supprimerProduits(CB_Supprimer.getValue());
         ObservableList<String> Libelle = FXCollections.observableArrayList(ss.afficherALLLibelle());
       CB_Supprimer.setItems(Libelle);
         JOptionPane.showMessageDialog(null,"Produit Supprimée");
         System.out.println(CB_Supprimer.getValue());
         
          clean();
          
    }
     
    private void clean() {
        
        tf_id.setText(null);
        tf_libelle.setText(null);
        tf_type.setText(null);
        tf_quantites.setText(null);
        tf_prix.setText(null);
        tf_id1.setText(null);
        tf_libelle1.setText(null);
        tf_type1.setText(null);
        tf_quantites1.setText(null);
        tf_prix1.setText(null);
    }

    @FXML
    private void recherche1(MouseEvent event) {
        
            ProduitsCrud ss = new ProduitsCrud();
           ObservableList<String> listerecherche = FXCollections.observableArrayList(ss.afficherALLLibelle());
               TextFields.bindAutoCompletion(rechercheee,listerecherche);

        Produits UserModif = ss.rechercherLibelle(rechercheee.getText());
        tf_id1.setText(String.valueOf(UserModif.getId()));
        tf_libelle1.setText(UserModif.getLibelle());
        tf_type1.setText(UserModif.getType());
        tf_quantites1.setText(String.valueOf(UserModif.getQuantites()));
        tf_prix1.setText(String.valueOf(UserModif.getPrix()));
       
        //rechercheee.setText(null);
    }

    @FXML
    private void fonctionStat(Event event) {
        float cv=0;float cn=0; float ce=0;
        ProduitsCrud p=new ProduitsCrud();
                 List <Produits> listp = p.afficherProduits();
                 for( int i=0;i<listp.size();i++)
                 { if (listp.get(i).getType().equals("vetements"))
                     cv+=listp.get(i).getQuantites();
                else if (listp.get(i).getType().equals("nutritions"))
                     cn+=listp.get(i).getQuantites();
                else ce+=listp.get(i).getQuantites();
               
                 
                 }
                 details.addAll(new PieChart.Data("Produits nutritions\n" +""+(cn/(cn+cv+ce))*100+"%",cn ),new PieChart.Data("Produits vetements\n" +""+(cv/(cn+cv+ce))*100+"%",cv ),new PieChart.Data("Produits equipements\n" +""+(ce/(cn+cv+ce))*100+"%",ce ))  ;
                 piechart=new PieChart();
                 piechart.setData(details);
                 piechart.setTitle("statistique des produits");
                 piechart.setLabelsVisible(true);
                 
                 pane.setCenter(piechart);
                 
                 
    }

}
