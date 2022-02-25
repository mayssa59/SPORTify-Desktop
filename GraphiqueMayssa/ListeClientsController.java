/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphiqueMayssa;

import Entite.Utilisateur;
import Service.ServiceClients;
import Service.ServiceCoaches;
import Utils.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
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

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ListeClientsController implements Initializable {

    @FXML
    private TableView<Utilisateur> tableClient;
    @FXML
    private TableColumn<Utilisateur, String> surnom_client;
    @FXML
    private TableColumn<Utilisateur, String> nom_client;
    @FXML
    private TableColumn<Utilisateur, String> prenom_client;
    @FXML
    private TableColumn<Utilisateur, String> tel_client;
    @FXML
    private TableColumn<Utilisateur, String> mail_client;
    @FXML
    private TableColumn<Utilisateur, String> sexe_client;
    @FXML
    private TextField tfTxt;
    
    
     public ObservableList<Utilisateur> data=FXCollections.observableArrayList() ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceClients S3 = new ServiceClients();
        List<Utilisateur> clients = new ArrayList();
        try {
            clients=S3.afficherClients();
            surnom_client.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("surnom"));
            nom_client.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("nom"));
            prenom_client.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("prenom"));
            tel_client.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("numeroDeTelephone"));
            mail_client.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("email"));
            sexe_client.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("sexe"));
            data.clear();
            for (int i = 0; i < clients.size(); i++) {
                data.add(clients.get(i));
            }
            tableClient.setItems(data);
      
            
        } catch (SQLException ex) {
            System.out.println("error");
        }
        
        
       
        tfTxt.getText();

        
        FilteredList<Utilisateur> filteredData = new FilteredList<>(data, b -> true);
        tfTxt.textProperty().addListener((observable, oldValue, newValue) -> {
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
        sortedData.comparatorProperty().bind(tableClient.comparatorProperty());
        tableClient.setItems(sortedData);
    
    }
    
        

    
       
    
    
}
    
