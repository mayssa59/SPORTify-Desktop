/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphiqueMayssa;

import Entite.Cours;
import Entite.Utilisateur;
import Service.ServiceClients;
import Service.ServiceCoachDansCours;
import Service.ServiceCoaches;
import Utils.DataSource;
import static java.lang.Math.E;
import static java.lang.StrictMath.E;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author asus
 */

public class ListesCoachController implements Initializable {
    public Cours cours; 
     @FXML
    private TableView<Utilisateur> tableCoach;
    @FXML
    private TableColumn<Utilisateur, String> surnom_coach;
    @FXML
    private TableColumn<Utilisateur, String> nom_coach;
    @FXML
    private TableColumn<Utilisateur, String> prenom_coach;
    @FXML
    private TableColumn<Utilisateur, String> tel_coach;
    @FXML
    private TableColumn<Utilisateur, String> mail_coach;
    @FXML
    private TableColumn<Utilisateur, Float> evaluation_coach;
 
    @FXML
    private TextField txtName;
    
 public ObservableList<Utilisateur> data=FXCollections.observableArrayList() ;
 



    @FXML
    private ImageView myimageview2;

   
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ServiceCoaches S2 = new ServiceCoaches();
        List<Utilisateur> coaches = new ArrayList();
        try {
            coaches=S2.afficherCoaches();
            surnom_coach.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("surnom"));
            nom_coach.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("nom"));
            prenom_coach.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("prenom"));
            tel_coach.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("numeroDeTelephone"));
            mail_coach.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("email"));
            evaluation_coach.setCellValueFactory(new PropertyValueFactory<Utilisateur,Float>("evaluationCoach"));
            data.clear();
            for (int i = 0; i < coaches.size(); i++) {
                data.add(coaches.get(i));
            }
            tableCoach.setItems(data);
      
            
        } catch (SQLException ex) {
            System.out.println("error");
        }
    txtName.getText();

        
        FilteredList<Utilisateur> filteredData = new FilteredList<>(data, b -> true);
        txtName.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Utilisateur -> {
                            if (newValue == null || newValue.isEmpty()) {
					return true;
                        }
        String lowerCaseFilter = newValue.toLowerCase();
				
				if (Utilisateur.getSurnom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (Utilisateur.getSurnom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(Utilisateur.getSurnom()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; 
                                });
                        
		});
        
        SortedList<Utilisateur> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableCoach.comparatorProperty());
        tableCoach.setItems(sortedData);
    }    
    
    public void setCours(Cours c)
    {
        this.cours=c;
        System.out.println("after setting cours in second controller :"+this.cours);
    }

    @FXML
    private void affecterAction(ActionEvent event) throws SQLException {
         TrayNotification tray = null;
ServiceCoachDansCours S1 = new ServiceCoachDansCours();
int selectedIndex = tableCoach.getSelectionModel().getSelectedIndex();
        Utilisateur u = (Utilisateur) tableCoach.getSelectionModel().getSelectedItem();
        
        System.out.println("test :"+selectedIndex);
        if (selectedIndex!=-1) {
            S1.ajouterCoachDansCours(cours, u);
            Stage stage = (Stage) tableCoach.getScene().getWindow();
             tray = new TrayNotification("SPORTify", "Coach affecté", NotificationType.SUCCESS);
      
        tray.showAndDismiss(Duration.seconds(6));
        }
    }

  
    @FXML
    private void modifierAction(ActionEvent event) throws SQLException {
         TrayNotification tray = null;
        ServiceCoachDansCours S1 = new ServiceCoachDansCours();
int selectedIndex = tableCoach.getSelectionModel().getSelectedIndex();
        Utilisateur u = (Utilisateur) tableCoach.getSelectionModel().getSelectedItem();
        
        System.out.println("test :"+selectedIndex);
        if (selectedIndex!=-1) {
            S1.modifierCoachDansCours(cours, u);
            Stage stage = (Stage) tableCoach.getScene().getWindow();
             tray = new TrayNotification("SPORTify", "Coach modifié", NotificationType.SUCCESS);
      
        tray.showAndDismiss(Duration.seconds(6));
        }
        
    }
   
    

}